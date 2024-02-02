package com.example.pharmdemo.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Column(name = "email", nullable = false)
    @NotBlank(message = "Enter your email")
    private String username;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Enter your password")
    private String password;
}
