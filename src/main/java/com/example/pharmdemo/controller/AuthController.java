package com.example.pharmdemo.controller;

import com.example.pharmdemo.dto.request.LoginRequest;
import com.example.pharmdemo.dto.request.LogoutRequest;
import com.example.pharmdemo.dto.request.RegistrationRequest;
import com.example.pharmdemo.dto.request.UserRequest;
import com.example.pharmdemo.dto.respone.*;
import com.example.pharmdemo.service.serviceImpl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.pharmdemo.utils.AuthEndpoints.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH_CONTROLLER)
public class AuthController {


    private final AuthenticationServiceImpl authenticationService;
//    @PostMapping(SIGN_UP)
//    public ResponseEntity<ApiResponse<UserResponse>> signUp(@RequestBody UserRequest userRequest){
//        return new ResponseEntity<>(authenticationService.register(userRequest), HttpStatus.CREATED);
//    }
//
//    @PostMapping(LOGIN)
//    public ResponseEntity<ApiResponse<UserResponse>> login(@PathVariable String username){
//        return new ResponseEntity<>(authenticationService.login(username), HttpStatus.OK);
//    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> register(@RequestBody RegistrationRequest request){
        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<LoginResponse>> authenticate(@RequestBody LoginRequest request){
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);

    }

    @PostMapping(LOGOUT)
    public ResponseEntity<ApiResponse<LogoutResponse>> logout(@RequestBody LogoutRequest logoutRequest){
        return new ResponseEntity<>(authenticationService.logout(logoutRequest), HttpStatus.OK);
    }



}
