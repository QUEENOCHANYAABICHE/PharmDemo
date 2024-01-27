package com.example.pharmdemo.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
            "[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\." +
            "[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isValid(String email){
        Pattern pattern = Pattern.compile(email);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
