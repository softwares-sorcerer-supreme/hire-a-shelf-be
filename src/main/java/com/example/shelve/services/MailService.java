package com.example.shelve.services;

import com.example.shelve.dto.request.DataMailRequest;
import javax.mail.MessagingException;

public interface MailService {
    void sendMail(DataMailRequest dataMailRequest);
}
