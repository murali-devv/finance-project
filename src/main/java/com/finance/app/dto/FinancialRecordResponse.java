package com.finance.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@Schema(description = "Response object representing a financial record (income or expense)")
public class FinancialRecordResponse {

    @Schema(
            description = "Unique identifier of the record",
            example = "101"
    )
    private Long id;

    @Schema(
            description = "Title of the financial record",
            example = "Monthly Salary"
    )
    private String title;

    @Schema(
            description = "Transaction amount",
            example = "25000.0"
    )
    private Double amount;

    @Schema(
            description = "Type of transaction (INCOME or EXPENSE)",
            example = "INCOME"
    )
    private String type;

    @Schema(
            description = "Category of transaction (FOOD, RENT, SALARY etc.)",
            example = "SALARY"
    )
    private String category;

    @Schema(
            description = "Date of the transaction",
            example = "2026-04-23"
    )
    private LocalDate date;
}