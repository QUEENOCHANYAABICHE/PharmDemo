package com.example.pharmdemo.service.serviceImpl;

import com.example.pharmdemo.dto.request.UserRequest;
import com.example.pharmdemo.dto.respone.ApiResponse;
import com.example.pharmdemo.dto.respone.UserResponse;
import com.example.pharmdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public ApiResponse<UserResponse> signUp(UserRequest userRequest) {
        return null;
    }
}
