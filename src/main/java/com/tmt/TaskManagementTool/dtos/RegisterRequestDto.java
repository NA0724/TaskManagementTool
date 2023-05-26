package com.tmt.TaskManagementTool.dtos;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class RegisterRequestDto {
    @NonNull
    private String username;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String confirmPassword;
}
