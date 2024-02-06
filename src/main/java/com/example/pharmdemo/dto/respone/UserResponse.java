package com.example.pharmdemo.dto.respone;

import com.example.pharmdemo.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private Boolean status;

    private int age;

    private Role role;

    private String phoneNumber;

    private String password;
}
