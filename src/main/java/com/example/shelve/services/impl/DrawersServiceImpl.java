package com.example.shelve.services.impl;

import com.example.shelve.repository.DrawersRepository;
import com.example.shelve.services.DrawersService;
import org.springframework.beans.factory.annotation.Autowired;

public class DrawersServiceImpl implements DrawersService {

    @Autowired
    private DrawersRepository drawersRepository;
}
