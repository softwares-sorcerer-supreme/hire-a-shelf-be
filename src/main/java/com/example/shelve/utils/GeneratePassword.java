package com.example.shelve.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class GeneratePassword {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]?";
    private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static final SecureRandom random = new SecureRandom();
    private static final int PASSWORD_LENGTH = 8;


    public String generatePassword() {
        StringBuilder passwordBuilder = new StringBuilder(PASSWORD_LENGTH);
        passwordBuilder.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
        passwordBuilder.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
        passwordBuilder.append(NUMBER.charAt(random.nextInt(NUMBER.length())));
        passwordBuilder.append(OTHER_CHAR.charAt(random.nextInt(OTHER_CHAR.length())));

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int rndCharAt = random.nextInt(PASSWORD_ALLOW_BASE.length());
            char rndChar = PASSWORD_ALLOW_BASE.charAt(rndCharAt);
            passwordBuilder.append(rndChar);
        }

        return passwordBuilder.toString();
    }

}
