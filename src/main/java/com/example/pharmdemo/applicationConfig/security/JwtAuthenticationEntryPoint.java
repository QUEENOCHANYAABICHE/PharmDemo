package com.example.pharmdemo.applicationConfig.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        final Map<String, Object> body = new HashMap<>();
        body.put("error", "UnAuthorize Error" );
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("message", authException.getLocalizedMessage());
        body.put("path", request.getServletPath());
        new ObjectMapper().writeValue(response.getOutputStream(),body);

    }
}