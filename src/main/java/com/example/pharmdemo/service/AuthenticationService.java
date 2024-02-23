package com.example.pharmdemo.service;


import com.example.pharmdemo.dto.request.AuthenticationRequest;
import com.example.pharmdemo.dto.request.LoginRequest;
import com.example.pharmdemo.dto.request.LogoutRequest;
import com.example.pharmdemo.dto.request.RegistrationRequest;
import com.example.pharmdemo.dto.respone.ApiResponse;
import com.example.pharmdemo.dto.respone.AuthenticationResponse;
import com.example.pharmdemo.dto.respone.LoginResponse;
import com.example.pharmdemo.dto.respone.LogoutResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthenticationService {
   ApiResponse<AuthenticationResponse> register(@RequestBody RegistrationRequest request);
//    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
//
    ApiResponse<LoginResponse> authenticate(LoginRequest loginRequest);

    ApiResponse<LogoutResponse> logout(LogoutRequest logoutRequest);
}
