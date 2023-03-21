package com.example.shelve.services;

import com.example.shelve.dto.request.ChangePasswordRequest;
import com.example.shelve.dto.response.AccountResponse;

import java.util.List;

public interface AccountService {

    List<AccountResponse> getAllAccount();

    AccountResponse getAccount(Long id);

    String changePassword(ChangePasswordRequest changePasswordRequest);

    String forgetPassword(ChangePasswordRequest changePasswordRequest);
}
