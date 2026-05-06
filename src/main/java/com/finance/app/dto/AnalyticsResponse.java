package com.finance.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object containing financial analytics summary")
public class AnalyticsResponse {

    @Schema(
            description = "Total income of the user",
            example = "50000.0"
    )
    private Double totalIncome;

    @Schema(
            description = "Total expense of the user",
            example = "30000.0"
    )
    private Double totalExpense;

    @Schema(
            description = "Remaining balance (income - expense)",
            example = "20000.0"
    )
    private Double balance;
}