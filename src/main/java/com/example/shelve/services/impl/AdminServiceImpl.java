package com.example.shelve.services.impl;

import com.example.shelve.repository.AdminRepository;
import com.example.shelve.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
}
