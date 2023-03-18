package com.example.shelve.entities.enums;

public enum ENotificationType {
    APPROVED("Approved"),
    DECLINED("Declined"),
    ANNOUNCE("Announce");
    private String name;

    ENotificationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
