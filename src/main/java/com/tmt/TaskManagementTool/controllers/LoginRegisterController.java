package com.tmt.TaskManagementTool.controllers;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.TaskManagementTool.dto.LoginDto;
import com.tmt.TaskManagementTool.dto.RegisterDto;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class LoginRegisterController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger = Logger.getLogger(LoginRegisterController.class.getName());;

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
        
        logger.info("inside login function");

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto){


       // add check for username exists in a DB
        if(userService.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }
        // create user object
        User user = new User();
        user.setFirstname(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        userService.createUser(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
}
