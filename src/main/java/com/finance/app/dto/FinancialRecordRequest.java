package com.finance.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Request object used to create a financial record (income or expense)")
public class FinancialRecordRequest {

    @NotBlank(message = "Title is required")
    @Schema(
            description = "Title of the financial record",
            example = "Salary for April"
    )
    private String title;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @Schema(
            description = "Amount of the transaction",
            example = "25000.0"
    )
    private Double amount;

    @NotBlank(message = "Type is required")
    @Schema(
            description = "Type of transaction (INCOME or EXPENSE)",
            example = "INCOME"
    )
    private String type;

    @NotBlank(message = "Category is required")
    @Schema(
            description = "Category of expense/income (FOOD, RENT, SALARY etc.)",
            example = "SALARY"
    )
    private String category;

    @NotNull(message = "Date is required")
    @Schema(
            description = "Date of transaction",
            example = "2026-04-23"
    )
    private LocalDate date;
}