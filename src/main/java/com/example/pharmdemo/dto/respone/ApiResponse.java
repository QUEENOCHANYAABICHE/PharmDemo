package com.example.pharmdemo.dto.respone;

import com.example.pharmdemo.validations.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T> {
    private String message;
    private String time;
    private int statusCode;
    private T data;

    public ApiResponse(String message, int statusCode, T data) {
        this.message = message;
        this.time = DateUtils.savaDate(LocalDateTime.now());
        this.statusCode = statusCode;
        this.data = data;
    }
}
