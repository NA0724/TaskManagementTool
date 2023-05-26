package com.tmt.TaskManagementTool.dtos;

import lombok.Data;

import org.springframework.lang.NonNull;

@Data
public class LoginRequestDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
