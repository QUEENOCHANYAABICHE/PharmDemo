package com.example.pharmdemo.service.serviceImpl;


import com.example.pharmdemo.applicationConfig.security.JwtService;
import com.example.pharmdemo.dto.request.LoginRequest;
import com.example.pharmdemo.dto.request.LogoutRequest;
import com.example.pharmdemo.dto.request.RegistrationRequest;
import com.example.pharmdemo.dto.respone.ApiResponse;
import com.example.pharmdemo.dto.respone.AuthenticationResponse;
import com.example.pharmdemo.dto.respone.LoginResponse;
import com.example.pharmdemo.dto.respone.LogoutResponse;
import com.example.pharmdemo.enums.ResponseCode;
import com.example.pharmdemo.enums.Role;
import com.example.pharmdemo.exceptions.*;
import com.example.pharmdemo.models.Token;
import com.example.pharmdemo.models.User;
import com.example.pharmdemo.registrationEvent.UserRegistrationEvent;
import com.example.pharmdemo.repository.TokenRepository;
import com.example.pharmdemo.repository.UserRepository;
import com.example.pharmdemo.service.AuthenticationService;
import com.example.pharmdemo.validations.Validations;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher publisher;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final TokenServiceImpl tokenServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final Validations<RegistrationRequest> registrationRequestValidationUtils;
    private final Validations<LoginRequest> loginRequestValidations;



    @Override
    public ApiResponse<AuthenticationResponse>  register(RegistrationRequest request) {
        registrationRequestValidationUtils.validate(request);
       if(userRepository.findByUsername(request.getUsername()).isPresent()){
           throw new UserAlreadyExistsException("uSER aLREADY eXIST");
       }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .status(false)
                .age(request.getAge())
                .role(Role.ROLE_USER)
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);

        Token token = tokenServiceImpl.generateOtp(user);
        String otp = token.getOtp();
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiresAt(token.getCreatedAt().plusMinutes(5));
//        token.setOtp(otp);
        token.setUser(user);
        tokenServiceImpl.saveOtp(token);
            tokenServiceImpl.sendOtp(user, otp, token);


        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .username(user.getUsername())
                .message("Registration successful")

                .build();

        return new ApiResponse<>(ResponseCode.USER_REGISTERED_SUCCESSFULLY.getMessage(),ResponseCode.USER_REGISTERED_SUCCESSFULLY.getCode(), authenticationResponse);
    }

    @Override
    public ApiResponse<LoginResponse> authenticate(LoginRequest loginRequest) {
        loginRequestValidations.validate(loginRequest);
        User user = getUserByUsername(loginRequest.getUsername());

        if (user.getStatus().equals(false)) {
            throw new UserDisabledException("Password does not match");
        }

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid Password");
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()
        );

        String jwtAccessToken = jwtService.generateToken(user);
        String jwtRefreshToken = jwtService.generateRefreshToken(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginResponse loginResponse = LoginResponse.builder()
                .username(user.getUsername())
                .accessToken(jwtAccessToken)
                .refreshToken(jwtRefreshToken)
                .build();

        return new ApiResponse<>(ResponseCode.LOGIN_SUCCESSFULLY_RESPONSE.getMessage(), ResponseCode.LOGIN_SUCCESSFULLY_RESPONSE.getCode(), loginResponse);

//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        var user = userRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new UsernameNotFoundException("The user email does not exist"));
//
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//
//
    }

    @Override
    public ApiResponse<LogoutResponse> logout(LogoutRequest logoutRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();

            User user = userRepository.findByUsername(username).get();
            System.out.println(user);
            revokeAllToken(user);
        }

        LogoutResponse logoutResponse = LogoutResponse.builder()
                .message("You were logged out successfully")
                .build();
        return new ApiResponse<>(ResponseCode.LOGOUT_SUCCESSFULLY_RESPONSE.getMessage(),ResponseCode.LOGOUT_SUCCESSFULLY_RESPONSE.getCode(),logoutResponse);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void validateUserExistence(String username){
         userRepository.findByUsername(username)
                .orElseThrow(()-> new UserAlreadyExistsException("User is found"))
                ;

    }

    private void revokeAllToken(User user) {
        List<Token> tokenList = tokenRepository.findAllValidTokenByUser(user.getId());
        if (tokenList.isEmpty()) {
            return;
        }
        for (Token token : tokenList) {
            token.setRevoked(true);
            token.setExpired(true);
            tokenRepository.saveAll(tokenList);
        }
    }
}
