package com.example.shelve.controllers;

import com.example.shelve.dto.response.AdminResponse;
import com.example.shelve.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<List<AdminResponse>> getAllAdmin(){
        return new ResponseEntity<>(adminService.getAllAdmin(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse> getAdmin(@PathVariable  Long id){
        return new ResponseEntity<>(adminService.getAdmin(id), HttpStatus.FOUND);
    }
}
