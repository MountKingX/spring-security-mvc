package com.kangmin.security.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AccountRegisterForm {

    @NotNull (message = "is required")
    @Size(min = 2, message = "minimum two characters")
    @Size(max = 32, message = "maximum is 32 characters")
    private String username;

    @NotNull (message = "is required")
    @Size(min = 2, message = "minimum two characters")
    @Size(max = 32, message = "maximum is 32 characters")
    private String password;

    @NotNull (message = "is required")
    @Size(min = 3, message = "minimum three characters")
    @Size(max = 32, message = "maximum is 32 characters")
    private String password2;

    private String role;
}
