package com.example.pharmdemo.service;

import com.example.pharmdemo.dto.request.OtpVerificationRequest;
import com.example.pharmdemo.dto.respone.ApiResponse;
import com.example.pharmdemo.models.Token;
import com.example.pharmdemo.models.User;

public interface TokenService {

    Token generateOtp(User user);
    void sendOtp(User user, String otp, Token newConfirmationToken);
    ApiResponse<String> verifyUserOtp(OtpVerificationRequest otpVerificationRequest);
    void saveOtp(Token confirmationToken);
    String verifyToken(String token);
    boolean isOtpExpired(Token confirmationToken);

}
