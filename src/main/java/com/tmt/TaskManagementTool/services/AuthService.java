package com.tmt.TaskManagementTool.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.dtos.ApiResponse;
import com.tmt.TaskManagementTool.dtos.AuthenticationResponse;
import com.tmt.TaskManagementTool.dtos.LoginRequestDto;
import com.tmt.TaskManagementTool.dtos.RegisterRequestDto;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.models.VerificationToken;
import com.tmt.TaskManagementTool.repositories.UserRepository;
import com.tmt.TaskManagementTool.repositories.VerificationTokenRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;
    
    @Autowired
    private BeanFactory beanFactory;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
 
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    private AuthenticationManager getAuthenticationManager() {     
        if (this.authenticationManager == null) {       
            this.authenticationManager = beanFactory.getBean(BeanIds.AUTHENTICATION_MANAGER, AuthenticationManager.class);     
        }     
        return this.authenticationManager;   
    } 

    @Bean
    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); 
    }

    public boolean existsByUserName(RegisterRequestDto registerRequestDto) {
        return userRepository.existsByUsername(registerRequestDto.getUsername());
    }

    public void createUser(RegisterRequestDto registerRequestDto) {
       String password = passwordEncoder().encode(registerRequestDto.getPassword());
        User user = new User();
        user.setFirstname(registerRequestDto.getFirstname());
        user.setLastname(registerRequestDto.getLastname());
        user.setEmail(registerRequestDto.getEmail());
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(password);
        userRepository.save(user);
        log.info("Saved User to Database");
    }

    

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        
        return token;
    }

    public AuthenticationResponse authenticate(LoginRequestDto loginRequestDto) {
        Authentication authenticate = getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String accessToken = jwtTokenProvider.generateToken(authenticate);
        return new AuthenticationResponse(accessToken, loginRequestDto.getUsername());
    }

    Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }

    public ApiResponse verifyAccount(String token) {
        Optional<VerificationToken> verificationTokenOptional = verificationTokenRepository.findByToken(token);
        if (verificationTokenOptional.isPresent()) {
            fetchUserAndEnable(verificationTokenOptional.get());
            return new ApiResponse(200, "User is Enabled");
        } else {
            return new ApiResponse(400, "Invalid Token");
        }
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.getUserByUsername(username).orElseThrow();
        userRepository.save(user);
    }

    
}
