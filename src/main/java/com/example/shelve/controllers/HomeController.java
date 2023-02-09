package com.example.shelve.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String test() {
        return "Hello-world";
    }

    @GetMapping("test2")
    public String test2() {
        return "Deploy duoc roi neeeee";
    }


}
