package com.finance.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request object used for user registration")
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    @Schema(
            description = "Full name of the user",
            example = "Ravi Kumar"
    )
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(
            description = "Valid email address of the user",
            example = "ravi@gmail.com"
    )
    private String email;

    @NotBlank(message = "Password is required")
    @Schema(
            description = "Password for account creation",
            example = "StrongPass@123"
    )
    private String password;


}