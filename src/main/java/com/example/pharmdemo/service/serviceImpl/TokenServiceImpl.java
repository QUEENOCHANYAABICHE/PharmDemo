package com.example.pharmdemo.service.serviceImpl;


import com.example.pharmdemo.dto.request.OtpVerificationRequest;
import com.example.pharmdemo.dto.respone.ApiResponse;
import com.example.pharmdemo.enums.ResponseCode;
import com.example.pharmdemo.exceptions.ExpiredOtpException;
import com.example.pharmdemo.exceptions.InvalidCredentialsException;
import com.example.pharmdemo.exceptions.OtpException;
import com.example.pharmdemo.exceptions.UserNotFoundException;
import com.example.pharmdemo.models.Token;
import com.example.pharmdemo.models.User;
import com.example.pharmdemo.registrationEvent.UserRegistrationEvent;
import com.example.pharmdemo.repository.TokenRepository;
import com.example.pharmdemo.repository.UserRepository;
import com.example.pharmdemo.service.TokenService;
import com.example.pharmdemo.utils.RandomGeneratedValue;
import com.example.pharmdemo.utils.UserVerification;
import com.example.pharmdemo.validations.Validations;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserVerification userVerification;
    private final Validations<OtpVerificationRequest> otpVerificationRequestValidations;


    @Override
    public Token generateOtp(User user) {
         String otp = RandomGeneratedValue.generateRandomValues();
         return new Token(otp, user);
    }

    @Override
    public void sendOtp(User user, String otp, Token newConfirmationToken) {
        Token foundConfirmationToken = confirmationTokenRepository.findByUserId(user.getId());

        if(foundConfirmationToken != null){
            confirmationTokenRepository.delete(foundConfirmationToken);
        }
        confirmationTokenRepository.save(newConfirmationToken);
try {
    applicationEventPublisher.publishEvent(new UserRegistrationEvent(user, otp));
}
        catch(RuntimeException e){
            throw new OtpException("Could not send mail");
        }
    }

    @Override
    public ApiResponse<String> verifyUserOtp(OtpVerificationRequest otpVerificationRequest) {
        otpVerificationRequestValidations.validate(otpVerificationRequest);
        User user = userVerification.verifyUserByUsername(otpVerificationRequest.getUsername());

        Token confirmationTokenConfirmation = confirmationTokenRepository.findByUser_UsernameAndOtp(user.getUsername(), otpVerificationRequest.getOtp());
        if(confirmationTokenConfirmation == null && isOtpExpired(confirmationTokenConfirmation)){
            throw new InvalidCredentialsException("Invalid or Expired credential");
        }
        user.setStatus(true);
        userRepository.save(user);
        return new ApiResponse<>(ResponseCode.VERIFICATION_SUCCESS_RESPONSE.getMessage(),ResponseCode.VERIFICATION_SUCCESS_RESPONSE.getCode(), "account verified");
    }

    @Override
    public void saveOtp(Token confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public String verifyToken(String token) {
        Token confirmationToken = confirmationTokenRepository.findByOtp(token)
        .orElseThrow(() -> new UserNotFoundException("Invalid credentials"));
        if(isOtpExpired(confirmationToken)){
            confirmationTokenRepository.delete(confirmationToken);
            throw new ExpiredOtpException("OTP is Expired");
        }
        return confirmationToken.getUser().getUsername();
    }

    @Override
    public boolean isOtpExpired(Token confirmationToken) {
        LocalDateTime otpCreatedAt = confirmationToken.getCreatedAt();
        LocalDateTime currentDateTime = LocalDateTime.now();
        Duration duration = Duration.between(otpCreatedAt,currentDateTime);
        long minutesPassed = duration.toMinutes();
        long otpExpiresAt = 4;
        return minutesPassed > otpExpiresAt;
    }
}
