package com.example.shelve.dto.request;

import java.util.List;

public class PushNotificationToMultipleDevicesRequest {
    private String title;
    private String body;
    private List<String> tokens;

    public PushNotificationToMultipleDevicesRequest(String title, String body, List<String> tokens) {
        this.title = title;
        this.body = body;
        this.tokens = tokens;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public List<String> getTokens() {
        return tokens;
    }
}
