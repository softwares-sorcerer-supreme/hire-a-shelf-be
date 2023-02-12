package com.example.shelve.services;

import com.example.shelve.dto.response.AdminResponse;

import java.util.List;

public interface AdminService {

    public List<AdminResponse> getAllAdmin();

    public  AdminResponse getAdmin(Long id);
}
