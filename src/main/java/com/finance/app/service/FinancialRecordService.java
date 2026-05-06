package com.finance.app.service;

import com.finance.app.dto.AnalyticsResponse;
import com.finance.app.dto.FinancialRecordRequest;
import com.finance.app.dto.FinancialRecordResponse;
import com.finance.app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;


public interface FinancialRecordService {

    FinancialRecordResponse create(FinancialRecordRequest request, Long userId);

    Page<FinancialRecordResponse> getAll(User user, org.springframework.data.domain.Pageable pageable);

    List<FinancialRecordResponse> getByType(User user, String type);

    List<FinancialRecordResponse> getByCategory(User user, String category);

    List<FinancialRecordResponse> getByDateRange(User user, LocalDate start, LocalDate end);



    Double getTotalIncome(User user);

    Double getTotalExpense(User user );

    AnalyticsResponse getTotalSummary(User user);

    AnalyticsResponse getMonthlySummary(User user, int month, int year);

}
