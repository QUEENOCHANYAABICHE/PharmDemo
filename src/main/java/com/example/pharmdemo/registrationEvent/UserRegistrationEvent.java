package com.example.pharmdemo.registrationEvent;

import com.example.pharmdemo.models.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class UserRegistrationEvent extends ApplicationEvent {
    private User user;
    private String otp;

    public UserRegistrationEvent(User user, String otp){
        super(user);
        this.user = user;
        this.otp = otp;
    }
}
