package com.example.pharmdemo.controller;

import com.example.pharmdemo.dto.request.UserRequest;
import com.example.pharmdemo.dto.respone.ApiResponse;
import com.example.pharmdemo.dto.respone.UserResponse;
import com.example.pharmdemo.service.serviceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.pharmdemo.utils.AuthEndpoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH_CONTROLLER)
public class AuthController {

    private final UserServiceImpl userService;
    @PostMapping(SIGN_UP)
    public ResponseEntity<ApiResponse<UserResponse>> signUp(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.signUp(userRequest), HttpStatus.CREATED);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<ApiResponse<UserResponse>> login(@PathVariable String userName){
        return new ResponseEntity<>(userService.login(userName), HttpStatus.OK);
    }
}
