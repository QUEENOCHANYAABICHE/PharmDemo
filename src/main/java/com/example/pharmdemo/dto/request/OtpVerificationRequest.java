package com.example.pharmdemo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerificationRequest {
   @NotBlank(message = "Email address must not be empty")
    private String username;

   @NotBlank(message = "OTP must not be empty")
    @Size(min = 4, max =4)
    private String otp;
}
