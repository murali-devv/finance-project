package com.finance.app.controller;

import com.finance.app.dto.*;
import com.finance.app.entity.User;
import com.finance.app.service.FinancialRecordService;
import com.finance.app.util.LoggedInUserUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
@Tag(
        name = "Financial Records APIs",
        description = "APIs for creating, fetching and filtering income/expense records"
)
public class FinancialRecordController {

    private final FinancialRecordService service;
    private final LoggedInUserUtil userUtil;


    @Operation(
            summary = "Create financial record",
            description = "Creates income or expense record for logged-in user"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Record created successfully")
    })
    @PostMapping
    public ApiResponse<?> create(@RequestBody FinancialRecordRequest request){
        User user = userUtil.getLoggedInUser();
        return ApiResponse.builder()
                .status("success")
                .message("Record created Successfully")
                .data(service.create(request,user.getId()))
                .build();
    }



    @Operation(
            summary = "Get all financial records",
            description = "Returns paginated list of user's financial records sorted by date (latest first)"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Records fetched successfully")
    })
    @GetMapping
    public ApiResponse<?> getAll(@PageableDefault(sort = "date", direction = Sort.Direction.DESC) Pageable pageable){

        User user = userUtil.getLoggedInUser();
        return ApiResponse.builder()
                .status("success")
                .message("success")
                .data(service.getAll(user,pageable))
                .build();
    }

    @Operation(
            summary = "Get records by type",
            description = "Filter records by type (INCOME or EXPENSE)"
    )
    @GetMapping("/type")
    public List<FinancialRecordResponse> getByType(@RequestParam String type) {

        User user = userUtil.getLoggedInUser();
        return service.getByType(user, type);
    }

    @Operation(
            summary = "Get records by category",
            description = "Filter records by category like FOOD, RENT, SALARY etc."
    )
    @GetMapping("/category")
    public List<FinancialRecordResponse> getByCategory(@RequestParam String category) {

        User user = userUtil.getLoggedInUser();
        return service.getByCategory(user, category);
    }

    @Operation(
            summary = "Get records by date range",
            description = "Fetch records between start date and end date"
    )
    @GetMapping("/date-range")
    public List<FinancialRecordResponse> getByDateRange(
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {

        User user = userUtil.getLoggedInUser();
        return service.getByDateRange(user, start, end);
    }
}

