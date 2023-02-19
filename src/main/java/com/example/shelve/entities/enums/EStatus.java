package com.example.shelve.entities.enums;

public enum EStatus {
    APPROVED("Approved"),
    DECLINED("Declined"),
    PENDING("Pending");

    private String name;

    EStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
