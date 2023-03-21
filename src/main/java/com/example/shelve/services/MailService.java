package com.example.shelve.services;

import com.example.shelve.dto.request.DataMailRequest;

public interface MailService {
    void sendMailApprovedAccount(DataMailRequest dataMailRequest);
    void sendMailDeclinedAccount(String email);
    void sendMailResetPassword(String email, String password);
}
