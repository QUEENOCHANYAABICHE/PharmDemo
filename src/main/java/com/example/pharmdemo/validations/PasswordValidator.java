package com.example.pharmdemo.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{8,20}$";

    public static boolean isValid(String password){
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}


