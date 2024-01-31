package com.example.pharmdemo.service.serviceImpl;

import com.example.pharmdemo.dto.request.UserRequest;
import com.example.pharmdemo.dto.respone.ApiResponse;
import com.example.pharmdemo.dto.respone.UserResponse;
import com.example.pharmdemo.enums.ResponseCode;
import com.example.pharmdemo.exceptions.InvalidEmailException;
import com.example.pharmdemo.exceptions.InvalidPasswordException;
import com.example.pharmdemo.exceptions.UserAlreadyExistsException;
import com.example.pharmdemo.mail.EmailService;
import com.example.pharmdemo.models.User;
import com.example.pharmdemo.repository.UserRepository;
import com.example.pharmdemo.service.UserService;
import com.example.pharmdemo.validations.EmailValidator;
import com.example.pharmdemo.validations.PasswordValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final HttpServletRequest request;
    private final EmailService emailService;


    @Override
    public ApiResponse<UserResponse> signUp(UserRequest userRequest) {
        if(!EmailValidator.isValid(userRequest.getUsername())){
            throw new InvalidEmailException("Invalid email format");
        }
        if(!PasswordValidator.isValid(userRequest.getPassword())){
            throw new InvalidPasswordException("Invalid password format");
        }

        Optional<User> findUser = userRepository.findByUsername(userRequest.getUsername());
        if(findUser.isPresent()){
            throw new UserAlreadyExistsException("User already exists");
        }




        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .username(userRequest.getUsername())
                .age(userRequest.getAge())
                .role(userRequest.getRole())
                .phoneNumber(userRequest.getPhoneNumber())
                .password(userRequest.getPassword())
                .build();

        userRepository.save(user);
        try {
            sendSignUpConfirmationMail(user.getUsername());
        } catch (Exception e){
            log.info("MAIL EXCEPTIONS");
            e.getMessage();
        }

       // String verificationToken = UUID.randomUUID().toString();

       //String url = request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
       //+ "/api/v1/auth/register/verify?token=" + verificationToken;



        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .age(user.getAge())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .build();

       return new ApiResponse<>(ResponseCode.USER_CREATED_SUCCESSFULLY.getMessage(), Instant.now(),ResponseCode.USER_CREATED_SUCCESSFULLY.getCode(), userResponse);

    }

    @Override
    public ApiResponse<UserResponse> login(String userName) {
//        Optional<User> findUser = userRepository.findByUserName(userName);
//
//        if(findUser.isEmpty()){
//            throw new AccountDoesNotException("Account does not exist");
//        }
//
//        User user = User.builder()
//                .firstName(findUser.get().getFirstName())
//                .lastName(findUser.get().getLastName())
//                .userName(findUser.get().getUserName())
//                .age(find)
//                .build();
return null;
    }

    public static User map2Entity(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setPassword(userRequest.getPassword());

        return user;
    }

    public void sendSignUpConfirmationMail(String userName){
        String subject = "SignUp Confirmation";
        String body = "Thank you for signing up! Your registration was successful";

        emailService.sendEmail(userName, subject, body);
    }
}
