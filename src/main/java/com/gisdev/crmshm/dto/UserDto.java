package com.gisdev.crmshm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserDto {

    @NotNull(message = "Username should not be empty.")
    private String username;

    @NotNull(message = "Password should not be empty.")
    private String password;
}
