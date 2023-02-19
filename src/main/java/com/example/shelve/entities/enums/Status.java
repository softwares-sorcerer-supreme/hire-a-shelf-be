package com.example.shelve.entities.enums;


public enum Status {
    APPROVED("Approved"),
    DECLINED("Declined"),
    PENDING("Pending");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
