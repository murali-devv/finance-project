package com.finance.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Generic API response wrapper used across all endpoints")
public class ApiResponse<T> {

    @Schema(
            description = "Response status (success / error)",
            example = "success"
    )
    private String status;

    @Schema(
            description = "Human readable message describing the result",
            example = "Record created successfully"
    )
    private String message;

    @Schema(
            description = "Actual response data returned by the API (generic type)"
    )
    private T data;
}