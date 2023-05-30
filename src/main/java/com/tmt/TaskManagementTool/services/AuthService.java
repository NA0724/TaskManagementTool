package com.tmt.TaskManagementTool.services;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.User;

@Service
public class AuthService {

    @Autowired
    private UserService userService;
    
    public String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        long expirationTimeInSeconds = 3600; // 1 hour in seconds

        // Set the current time and calculate the token's expiration date
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationDateTime = currentTime.plusSeconds(expirationTimeInSeconds);
        long expirationTimestamp = expirationDateTime.toEpochSecond(ZoneOffset.UTC);
        String token = Base64.getEncoder().encodeToString(valueToEncode.getBytes(StandardCharsets.UTF_8));
        String random = UUID.randomUUID().toString();
        String authHeader = "Basic " + random + ";" + expirationTimestamp + ";" + token;    
        return authHeader;
    }

    public String decode(String val){
        String auth = val.replace("Basic ", "");
        String[] parts = auth.split(";");
        String expirationtime = parts[1];
        byte[] credDecoded = Base64.getDecoder().decode(parts[2]);
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
