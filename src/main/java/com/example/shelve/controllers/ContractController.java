package com.example.shelve.controllers;

import com.example.shelve.dto.request.CampaignRequest;
import com.example.shelve.dto.request.ContractRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.CampaignResponse;
import com.example.shelve.dto.response.ContractResponse;
import com.example.shelve.entities.enums.EStatus;
import com.example.shelve.services.CampaignService;
import com.example.shelve.services.ContractService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contract")
public class ContractController {

    @Autowired
    private ContractService contractService;

    @GetMapping("/store")
    public APIResponse<List<ContractResponse>> getAllStoreContract(@RequestParam long storeId,
                                                                   @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                   @RequestParam(required = false, defaultValue = "") List<String> states) {
        return contractService.getAllStoreContract(storeId, page, states);
    }

    @GetMapping
    public ResponseEntity<List<ContractResponse>> getAllContract() {
        return new ResponseEntity<>(contractService.getAllContract(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractResponse> getContract(@PathVariable Long id) {
        return new ResponseEntity<>(contractService.getContract(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ContractResponse> createContract(@RequestBody ContractRequest contractRequest) throws FirebaseMessagingException {
        return new ResponseEntity<>(contractService.createContract(contractRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractResponse> approveContract(@PathVariable Long id,
                                                            @RequestParam EStatus status) {
        return new ResponseEntity<>(contractService.approveContract(id, status), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContractResponse> disableContract(@PathVariable Long id) {
        return new ResponseEntity<>(contractService.disableContract(id), HttpStatus.OK);
    }

}
