package com.example.shelve.services.impl;

import com.example.shelve.dto.request.DataMailRequest;
import com.example.shelve.services.MailService;

import com.example.shelve.services.ThymleafService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService {

    public final static class SEND_MAIL_SUBJECT {
        public final static String CLIENT_REGISTER = "XÁC NHẬN TẠO MỚI THÔNG TIN NGƯỜI DÙNG";
    }

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private ThymleafService thymleafService;

    @Value("${spring.mail.username}")
    private String email;

    @Override
    public void sendMailApprovedAccount(DataMailRequest dataMailRequest) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());


            helper.setSubject(SEND_MAIL_SUBJECT.CLIENT_REGISTER);
            helper.setFrom(email);

            helper.setTo(dataMailRequest.getEmail());
            Map<String, Object> variables = new HashMap<>();
            variables.put("user_name", dataMailRequest.getUserName());
            variables.put("password", dataMailRequest.getPassword());
            helper.setText(thymleafService.createContent("mail-sender-test.html", variables), true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMailDeclinedAccount(String email) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());


            helper.setSubject(SEND_MAIL_SUBJECT.CLIENT_REGISTER);
            helper.setFrom(email);

            Map<String, Object> variables = new HashMap<>();
            helper.setText(thymleafService.createContent("mail-sender-announce.html", variables), true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void sendMailResetPassword(String email, String password){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setSubject(SEND_MAIL_SUBJECT.CLIENT_REGISTER);
            helper.setFrom(email);
            Map<String, Object> variables = new HashMap<>();
            variables.put("password", password);
            helper.setText(thymleafService.createContent("mail-sender-reset-password.html", variables), true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}