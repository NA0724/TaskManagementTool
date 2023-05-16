package com.tmt.TaskManagementTool.security;

import java.io.IOException;

import com.google.gson.Gson;
import com.tmt.TaskManagementTool.exceptions.InvalidLoginResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
                
                InvalidLoginResponse loginResponse = new InvalidLoginResponse();
                String jsonLoginResponse = new Gson().toJson(loginResponse);
        
                response.setContentType("application/json");
                response.setStatus(401);
                response.getWriter().print(jsonLoginResponse);
    }
    
}
