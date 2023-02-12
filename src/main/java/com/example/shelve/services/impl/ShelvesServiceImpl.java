package com.example.shelve.services.impl;

import com.example.shelve.repository.ShelvesRepository;
import com.example.shelve.services.ShelvesService;
import org.springframework.beans.factory.annotation.Autowired;

public class ShelvesServiceImpl implements ShelvesService {

    @Autowired
    private ShelvesRepository shelvesRepository;
}
