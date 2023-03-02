package com.example.shelve.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateErrorResponse {
//    private int status;
    private Map<String, String> errorMap;
}
