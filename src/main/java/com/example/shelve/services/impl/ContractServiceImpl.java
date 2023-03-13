package com.example.shelve.services.impl;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.request.ContractRequest;
import com.example.shelve.dto.request.PushNotificationRequest;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ContractResponse;
import com.example.shelve.entities.Campaign;
import com.example.shelve.entities.Contract;
import com.example.shelve.entities.Notification;
import com.example.shelve.entities.Store;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.exception.BadRequestException;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.ContractMapper;
import com.example.shelve.repository.CampaignRepository;
import com.example.shelve.repository.ContractRepository;
import com.example.shelve.repository.NotificationRepository;
import com.example.shelve.repository.StoreRepository;
import com.example.shelve.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Service
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
    public ContractResponse createContract(ContractRequest contractRequest) {
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

        Contract contractSaved = contractRepository.save(contract);
        firebaseMessagingService.sendNotificationToToken(PushNotificationRequest.builder()
                .token("campaign.getBrand().getAccount().getFireBaseToken()")
                .message("Cửa hàng "+ store.getName() + " đã tham gia chiến dịch của bạn")
                .title(campaign.getTitle()).build());
        notificationRepository.save(Notification.builder()
                .title(campaign.getTitle())
                .body("Cửa hàng "+ store.getName() + " đã tham gia chiến dịch của bạn")
                .account(campaign.getBrand().getAccount())
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


}
