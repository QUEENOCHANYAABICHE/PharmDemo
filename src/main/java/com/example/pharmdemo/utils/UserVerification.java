package com.example.pharmdemo.utils;


import com.example.pharmdemo.exceptions.UserNotFoundException;
import com.example.pharmdemo.exceptions.UsernameNotFoundException;
import com.example.pharmdemo.models.User;
import com.example.pharmdemo.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
@Component
public class UserVerification {
    private final UserRepository userRepository;

    public User verifyUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public String getUsernameFromContext(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User validateLoginUser(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not Found"));

        if(!user.getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            throw new UsernameNotFoundException("This is not the log in user");
        }
        return user;
    }

}
