package com.example.shelve.services.impl;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.entities.*;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.exception.BadRequestException;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.CampaignMapper;
import com.example.shelve.repository.*;
import com.example.shelve.services.CampaignService;
import com.example.shelve.services.FirebaseMessagingService;
import com.example.shelve.services.StorageService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Service
@Slf4j
public class CampaignServiceImpl implements CampaignService {

    private static final int pageSize = 6;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    private StoreCategoryRepository storeCategoryRepository;
    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private CampaignProductRepository campaignProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CampaignShelvesTypeRepository campaignShelvesTypeRepository;
    @Autowired
    private ShelvesTypeRepository shelvesTypeRepository;
    @Autowired
    private StorageService storageService;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CampaignMapper campaignMapper;
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;
    @Autowired
    private FirebaseTokenRepository firebaseTokenRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    @Cacheable(value = "campaign")
    public List<CampaignResponse> getAllCampaign() {
        List<CampaignResponse> campaignResponses = new ArrayList<>();
        campaignRepository.findAll().forEach(x -> campaignResponses.add(campaignMapper.toCampaignResponse(x)));
        return campaignResponses;
    }

    @Override
    public CampaignResponse getCampaign(Long id) {
        return campaignMapper.toCampaignResponse(campaignRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found!")));
    }

    @Override
    public CampaignResponse createNewCampaign(CampaignRequest campaignRequest) {
        Campaign campaign = campaignMapper.toCampaign(campaignRequest);
        Brand brand = brandRepository.findById(campaignRequest.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found!"));

        campaign.setBrand(brand);
        campaign.setCreatedDate(new Date(System.currentTimeMillis()));

        if (campaign.getStartDate().before(campaign.getCreatedDate()))
            throw new BadRequestException("Start Date must be after Today!");

//        if (campaign.getExpirationDate().before(campaign.getStartDate()))
//            throw new BadRequestException("Expiration Date must be after Start Date");

        campaign.setEStatus(EStatus.PENDING);
        campaign.setImgURL(storageService.uploadFile(campaignRequest.getImgMultipart()));

        Campaign campaignSaved = campaignRepository.save(campaign);

        List<Product> listProduct = new ArrayList<>();
        //get list object product and check
        campaignRequest.getProducts().forEach(i -> listProduct.add(
                productRepository
                        .findById(i)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Product ID: " + i + " not existed!"))
        ));

        //add to CampaignProduct table
        listProduct.forEach(product -> {
            campaignProductRepository.save(CampaignProduct.builder()
                    .status(true)
                    .product(product)
                    .campaign(campaignSaved)
                    .build());
        });


        List<ShelvesType> listShelvesType = new ArrayList<>();
        //get list shelve type
        campaignRequest.getShelveTypes().forEach(i -> listShelvesType.add(
                shelvesTypeRepository
                        .findById(i)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Shelve Type ID: " + i + " not existed!"))
        ));

        //add to Campaign Shelve Type table
        listShelvesType.forEach(shelveType ->
                campaignShelvesTypeRepository.save(CampaignShelvesType.builder()
                        .campaign(campaignSaved)
                        .shelvesType(shelveType)
                        .status(true)
                        .build())
        );

        return campaignMapper.toCampaignResponse(campaign);
    }

    @Override
    public CampaignResponse approveCampaign(Long id, EStatus status) throws FirebaseMessagingException {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found!"));
        campaign.setEStatus(status);

        Campaign campaignSaved = campaignRepository.save(campaign);
        Set<FirebaseNotiToken> brandFirebaseNotiTokens
                = campaignSaved.getBrand().getAccount().getFireBaseNotiTokens();

        Set<FirebaseNotiToken> storeFirebaseTokens = firebaseTokenRepository.findAllByStatusAndAccountStoreLocationCity(true, campaign.getCity().toString());

        List<String> brandStringFCMs = new ArrayList<>();
        brandFirebaseNotiTokens.forEach(firebaseNotiToken -> {
            brandStringFCMs.add(firebaseNotiToken.getToken());
        });

        List<String> storeStringFCMs = new ArrayList<>();
        storeStringFCMs.add("asdsad");

        storeFirebaseTokens.forEach(storeFirebaseToken -> {
            storeStringFCMs.add(storeFirebaseToken.getToken());
        });

        firebaseMessagingService.sendNotifications("Campaign has been approved", "Your campaign with title " +
                campaignSaved.getTitle() + " has been approved", brandStringFCMs);

        firebaseMessagingService.sendNotifications("New campaign in your location!", "A new campaign in your location with title " +
                campaignSaved.getTitle() + " has been posted! Check it out now!", storeStringFCMs);
        return campaignMapper.toCampaignResponse(campaignSaved);
        }

    @Override
    public CampaignResponse updateCampaign(Long id, CampaignRequest campaignRequest) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found!"));

        if(campaign.getEStatus() != EStatus.PENDING)
            throw new BadRequestException("Can't update campaign after approve!");

        campaign.setTitle(campaignRequest.getTitle());
        campaign.setContent(campaignRequest.getContent());
        campaign.setStartDate(campaignRequest.getStartDate());
        campaign.setExpirationDate(campaignRequest.getExpirationDate());
        campaign.setDuration(campaignRequest.getDuration());
        campaign.setImgURL(storageService.uploadFile(campaignRequest.getImgMultipart()));

        campaignProductRepository.deleteAll(campaignProductRepository.findCampaignProductByCampaign_Id(campaign.getId()));
        campaignShelvesTypeRepository.deleteAll(campaignShelvesTypeRepository.findByCampaign_Id(campaign.getId()));

        Campaign campaignSaved = campaignRepository.save(campaign);


        List<Product> listProduct = new ArrayList<>();
        //get list object product and check
        campaignRequest.getProducts().forEach(i -> listProduct.add(
                productRepository
                        .findById(i)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Product ID: " + i + " not existed!"))
        ));

        //add to CampaignProduct table
        listProduct.forEach(product -> campaignProductRepository.save(CampaignProduct.builder()
                .status(true)
                .product(product)
                .campaign(campaignSaved)
                .build()));


        List<ShelvesType> listShelvesType = new ArrayList<>();
        //get list shelve type
        campaignRequest.getShelveTypes().forEach(i -> listShelvesType.add(
                shelvesTypeRepository
                        .findById(i)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Shelve Type ID: " + i + " not existed!"))
        ));

        //add to Campaign Shelve Type table
        listShelvesType.forEach(shelveType ->
                campaignShelvesTypeRepository.save(CampaignShelvesType.builder()
                        .campaign(campaignSaved)
                        .shelvesType(shelveType)
                        .status(true)
                        .build())
        );


        return campaignMapper.toCampaignResponse(campaignSaved);
    }

    @Override
    public CampaignResponse disableCampaign(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found!"));

        campaign.setEStatus(EStatus.DISABLE);
        Campaign campaignSaved = campaignRepository.save(campaign);

        return campaignMapper.toCampaignResponse(campaignSaved);
    }

    @Override
    public APIResponse<List<CampaignResponse>> getAllCampaignsWithFilter(Long brandId, String keyword, int page, List<String> statusListFilter) {

        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC , "createdDate");
        //This is stateList use to querry
        List<EStatus> stateList = new ArrayList<>();

        //Checking to see whether the state form frontend match any state from the EStatus
        EStatus[] eStatuses = EStatus.values();
        for (EStatus status : eStatuses) {
            if(statusListFilter.contains(status.getName())){
                stateList.add(status);
            }
        }

        Page<Campaign> result = campaignRepository.findByKeywordWithFilter
                (stateList, keyword.toLowerCase(), brandId ,pageable);
        List<CampaignResponse> campaignResponses = new ArrayList<>();
        result.toList().forEach((x -> campaignResponses.add(campaignMapper.toCampaignResponse(x))));
        return new APIResponse<>(result.getTotalPages(), campaignResponses);
    }

    @Override
    public APIResponse<List<CampaignResponse>> getListCampaignsWithFilterForHomePage(Long storeId, String keyword, int page, List<String> statusListFilter, String suggestBy) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC , "createdDate");
        //This is stateList use to querrt
        List<EStatus> stateList = new ArrayList<>();

        //Checking to see whether the state form frontend match any state from the EStatus
        EStatus[] eStatuses = EStatus.values();
        for (EStatus status : eStatuses) {
            if(statusListFilter.contains(status.getName())){
                stateList.add(status);
            }
        }

        Optional<Store> store = storeRepository.findById(storeId);

        if (store.isEmpty()){
            throw new BadRequestException("Store is not exist!");
        }

        List<String> categoriesName = new ArrayList<>();
        if(suggestBy.equals("category")){

            List<StoreCategory> storeCategories =
                    storeCategoryRepository.findAllByStoreId(storeId);
            storeCategories.forEach((storeCategory -> {
                categoriesName.add(storeCategory.getCategory().getName());
            } ));
        }

        Page<Campaign> result = campaignRepository.findByKeywordWithFilterForHomePage
                (stateList, keyword.toLowerCase(), categoriesName, store.get().getLocation().getCity(), pageable);

        List<CampaignResponse> campaignResponses = new ArrayList<>();
        result.toList().forEach((x -> campaignResponses.add(campaignMapper.toCampaignResponse(x))));
        return new APIResponse<>(result.getTotalPages(), campaignResponses);

    }

}
