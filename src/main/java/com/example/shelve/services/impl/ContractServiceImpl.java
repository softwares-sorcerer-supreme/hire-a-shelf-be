package com.example.shelve.services.impl;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.request.ContractRequest;
import com.example.shelve.dto.request.PushNotificationRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ContractResponse;
import com.example.shelve.entities.*;
import com.example.shelve.entities.enums.ENotificationType;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.exception.BadRequestException;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.ContractMapper;
import com.example.shelve.repository.CampaignRepository;
import com.example.shelve.repository.ContractRepository;
import com.example.shelve.repository.NotificationRepository;
import com.example.shelve.repository.StoreRepository;
import com.example.shelve.services.ContractService;
import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ContractServiceImpl implements ContractService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private FirebaseMessagingServiceImpl firebaseMessagingService;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private StoreRepository storeRepository;


    @Override
    public List<ContractResponse> getAllContract() {
        List<ContractResponse> contractResponses = new ArrayList<>();
        contractRepository.findAll().forEach(x -> contractResponses.add(contractMapper.toContractResponse(x)));
        return contractResponses;
    }

    @Override
    public ContractResponse getContract(Long id) {
        ContractResponse contractResponse = contractMapper.toContractResponse(contractRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found!")));
        return contractResponse;
    }

    @Override
    public ContractResponse createContract(ContractRequest contractRequest) throws FirebaseMessagingException {

        if (contractRepository.findByStoreIdAndCampaignId(contractRequest.getStoreId(), contractRequest.getCampaignId()).isPresent()){
            throw new BadRequestException("You have already applied for this campaign!");
        }
        Campaign campaign = campaignRepository.findById(
                contractRequest
                        .getCampaignId())
                .orElseThrow(() -> new ResourceNotFoundException("Campaign not found!"));


        Store store = storeRepository.findById(
                contractRequest
                        .getStoreId())
                .orElseThrow(() -> new ResourceNotFoundException("Store not found!"));

        Contract contract = Contract.builder()
                .description(contractRequest.getDescription())
                .campaign(campaign)
                .store(store)
                .createDate(new Date(System.currentTimeMillis()))
                .EStatus(EStatus.PENDING)
                .build();


        Set<FirebaseNotiToken> firebaseNotiTokenList = campaign.getBrand().getAccount().getFireBaseNotiTokens();
        List<String> brandStringFCMs = new ArrayList<>();
        firebaseNotiTokenList.forEach(firebaseNotiToken -> {
            if (firebaseNotiToken.getToken() != null && firebaseNotiToken.isStatus()){
                brandStringFCMs.add(firebaseNotiToken.getToken());
            }
        });

        Contract contractSaved = contractRepository.save(contract);
        firebaseMessagingService.sendNotifications("Someone Applied to your campaign", "Cửa hàng "+ store.getName() + " đã tham gia chiến dịch của bạn", brandStringFCMs);

        notificationRepository.save(Notification.builder()
                .title(campaign.getTitle())
                .body("Cửa hàng "+ store.getName() + " đã tham gia chiến dịch của bạn")
                .account(campaign.getBrand().getAccount())
                 .type(ENotificationType.ANNOUNCE)
                .build());

        return contractMapper.toContractResponse(contractSaved);
    }

    @Override
    public ContractResponse approveContract(Long id, EStatus status) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found!"));

        if(!contract.getStore().isStatus())
            throw new BadRequestException("This store has disabled!");

        switch(contract.getCampaign().getEStatus()) {
            case PENDING:
                throw new BadRequestException("This campaign is waiting for approved!");

            case DECLINED:
                throw new BadRequestException("This campaign has been declined");
        }

        if(status == EStatus.APPROVED) {

        }

        contract.setApprovalDate(new Date(System.currentTimeMillis()));
        Contract contractSaved = contractRepository.save(contract);

        return contractMapper.toContractResponse(contractSaved);
    }

    @Override
    public ContractResponse disableContract(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found!"));

        contract.setEStatus(EStatus.CANCEL);
        contract.setCancelDate(new Date(System.currentTimeMillis()));

        Contract contractSaved = contractRepository.save(contract);

        return contractMapper.toContractResponse(contractSaved);
    }

    @Override
    public APIResponse<List<ContractResponse>> getAllStoreContract(long storeId, int page, List<String> states) {
        Pageable pageable = PageRequest.of(page, 6, Sort.Direction.DESC, "createDate");

        List<EStatus> stateList = new ArrayList<>();
        //Checking to see whether the state form frontend match any state from the EStatus
        EStatus[] eStatuses = EStatus.values();
        for (EStatus status : eStatuses) {
            if (states.contains(status.getName())) {
                stateList.add(status);
            }
        }
        Page<Contract> result;
        log.error(String.valueOf(stateList.size()));
        if (stateList.isEmpty()){
            result = contractRepository.findAllByStoreId(storeId, pageable);
        }else{
            result = contractRepository.findAllByStoreIdAndEStatusIn(storeId, stateList, pageable);
        }
        List<ContractResponse> contractResponses = new ArrayList<>();
        result.forEach(contract -> {
            contractResponses.add(contractMapper.toContractResponse(contract));
        });

        return new APIResponse<>(result.getTotalPages(), contractResponses);
    }


}
