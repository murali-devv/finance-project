package com.finance.app.controller;

import com.finance.app.dto.AnalyticsResponse;
import com.finance.app.dto.ApiResponse;
import com.finance.app.entity.User;
import com.finance.app.service.FinancialRecordService;
import com.finance.app.util.LoggedInUserUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
@Tag(
        name = "Financial Analytics APIs",
        description = "APIs for income, expense and financial summaries"
)
public class AnalyticsController {

    private final FinancialRecordService service;
    private final LoggedInUserUtil userUtil;


    @Operation(
            summary = "Get financial summary",
            description = "Returns total income, expense and balance for logged-in user"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Summary fetched successfully"),
    })
    @GetMapping("/summary")
    public ApiResponse<?> getSummary(){
        User user = userUtil.getLoggedInUser();
        return ApiResponse.builder()
                .status("success")
                .message("Summary fetched")
                .data(service.getTotalSummary(user))
                .build();
    }


    @Operation(
            summary = "Get monthly financial report",
            description = "Returns income and expense breakdown for a specific month and year"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Monthly report fetched successfully")
    @GetMapping("/monthly")
    @PreAuthorize("hasAnyRole('USER','ANALYST','ADMIN')")
    public AnalyticsResponse getMonthly(
            @RequestParam int month,
            @RequestParam int year) {

        return service.getMonthlySummary(userUtil.getLoggedInUser(), month, year);
    }


    @Operation(
            summary = "Get total income",
            description = "Returns total income of logged-in user"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Income fetched successfully")
    @GetMapping("/income")
    @PreAuthorize("hasAnyRole('USER','ANALYST','ADMIN')")
    public Double getIncome() {
        User user = userUtil.getLoggedInUser();
        return service.getTotalIncome(user);
    }

    @Operation(
            summary = "Get total expense",
            description = "Returns total expense of logged-in user"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Expense fetched successfully")
    @GetMapping("/expense")
    @PreAuthorize("hasAnyRole('USER','ANALYST','ADMIN')")
    public Double getExpense() {
        User user = userUtil.getLoggedInUser();
        return service.getTotalExpense(user);
    }
}
