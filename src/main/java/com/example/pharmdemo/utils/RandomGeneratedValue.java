package com.example.pharmdemo.utils;

import lombok.Data;

import java.util.UUID;

@Data
public class RandomGeneratedValue {
    public static String generateRandomValues(){
        String generator = UUID.randomUUID().toString();
        return generator.substring(0,4).toLowerCase();
    }

}
