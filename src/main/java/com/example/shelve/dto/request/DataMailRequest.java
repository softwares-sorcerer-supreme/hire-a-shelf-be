package com.example.shelve.dto.request;

import lombok.*;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataMailRequest {

    private String userName;
    private String email;
    private String password;
}
