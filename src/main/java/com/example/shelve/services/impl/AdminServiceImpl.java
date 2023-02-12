package com.example.shelve.services.impl;

import com.example.shelve.dto.response.AdminResponse;
import com.example.shelve.exception.ResourceNotFoundException;
import com.example.shelve.mapper.AdminMapper;
import com.example.shelve.repository.AdminRepository;
import com.example.shelve.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper mapper;

    @Override
    public List<AdminResponse> getAllAdmin() {
        List<AdminResponse> adminResponses = new ArrayList<>();
        adminRepository.findAll().forEach(x-> adminResponses.add(mapper.toAdminResponse(x)));
        return adminResponses;
    }

    @Override
    public AdminResponse getAdmin(Long id) {
        AdminResponse adminResponse = mapper.toAdminResponse(adminRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found!")));
        return adminResponse;
    }
}
