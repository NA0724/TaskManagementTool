package com.tmt.TaskManagementTool.services;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.User;

@Service
public class AuthService {

    @Autowired
    private UserService userService;
    
    public String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }

    public String decode(String val){
        String[] authParts = val.split("\\s+");
        byte[] credDecoded = Base64.getDecoder().decode(authParts[1]);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        // credentials = username:password
        //final String[] values = credentials.split(":", 2);
        String userName=credentials.split(":")[0];
        return userName;
    }
    public User getCurrentUser(String val){
        String username = decode(val);
        return userService.getUserByUsername(username);
    }
}