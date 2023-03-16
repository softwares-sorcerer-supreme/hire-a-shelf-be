package com.example.shelve.services;

import java.util.Map;

public interface ThymleafService {
    String createContent(String template, Map<String, Object> variables);
}
