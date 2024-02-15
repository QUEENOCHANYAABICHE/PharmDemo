package com.example.pharmdemo.dto.request;


import com.example.pharmdemo.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {


    private String firstName;

    private String lastName;

    private String username;

    private int age;

    private Role role;

    private String phoneNumber;

    private String password;










}
