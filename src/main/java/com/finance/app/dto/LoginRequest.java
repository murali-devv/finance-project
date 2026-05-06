package com.finance.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request object used for user login authentication")
public class LoginRequest {

    @NotBlank(message = "Email is required")
    @Schema(
            description = "Registered email of the user",
            example = "user@gmail.com"
    )
    private String email;

    @NotBlank(message = "Password is required")
    @Schema(
            description = "User password for authentication",
            example = "StrongPassword123"
    )
    private String password;
}