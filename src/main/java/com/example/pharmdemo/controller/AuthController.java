package com.example.pharmdemo.controller;

import com.example.pharmdemo.dto.request.UserRequest;
import com.example.pharmdemo.dto.respone.ApiResponse;
import com.example.pharmdemo.dto.respone.UserResponse;
import com.example.pharmdemo.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.pharmdemo.utils.AuthEndpoints.AUTH_CONTROLLER;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH_CONTROLLER)
public class AuthController {

    private final UserServiceImpl userService;
    @PostMapping()
    public ResponseEntity<ApiResponse<UserResponse>> signUp(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.signUp(userRequest), HttpStatus.CREATED);
    }
}
