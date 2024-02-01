package com.example.pharmdemo.service;

import com.example.pharmdemo.dto.request.UserRequest;
import com.example.pharmdemo.dto.respone.ApiResponse;
import com.example.pharmdemo.dto.respone.UserResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {
    ApiResponse<UserResponse> signUp(@RequestBody UserRequest userRequest);
    ApiResponse<UserResponse> login(@PathVariable String userName);
}
