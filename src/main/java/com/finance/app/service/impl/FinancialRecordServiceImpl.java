package com.finance.app.service.impl;

import com.finance.app.dto.AnalyticsResponse;
import com.finance.app.dto.FinancialRecordRequest;
import com.finance.app.dto.FinancialRecordResponse;
import com.finance.app.entity.FinancialRecord;
import com.finance.app.entity.RecordType;
import com.finance.app.entity.User;
import com.finance.app.exception.UserNotFoundException;
import com.finance.app.mapper.FinancialRecordMapper;
import com.finance.app.repository.FinancialRecordRepository;
import com.finance.app.repository.UserRepository;
import com.finance.app.service.FinancialRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialRecordServiceImpl implements FinancialRecordService {

    private final FinancialRecordRepository recordRepo;
    private final UserRepository userRepo;
    private final FinancialRecordMapper mapper;

    @Override
    public FinancialRecordResponse create(FinancialRecordRequest request, Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));;

        FinancialRecord record = mapper.toEntity(request);
        record.setUser(user);

        return mapper.toResponse(recordRepo.save(record));
    }



    @Override
    public Page<FinancialRecordResponse> getAll(User user,Pageable pageable) {

        if(user.getRole().name().equals("ADMIN") || user.getRole().name().equals("ANALYST")){
            return recordRepo.findAll(pageable).map(mapper::toResponse);
        }
        return recordRepo.findByUserId(user.getId(), pageable)
                .map(mapper::toResponse);
    }


    @Override
    public List<FinancialRecordResponse> getByType(User user, String type) {

        RecordType recordType = RecordType.valueOf(type);

        if(user.getRole().name().equals("ADMIN")) {
            return recordRepo.findAll().stream()
                    .filter(r -> r.getType() == recordType)
                    .map(mapper::toResponse)
                    .toList();
        }

        return recordRepo.findByUserIdAndType(user.getId(), recordType)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }
    @Override
    public List<FinancialRecordResponse> getByCategory(User user, String category) {
        if(user.getRole().name().equals("ADMIN")){
            return recordRepo.findAll().stream()
                    .filter(r -> r.getCategory().equals(category))
                    .map(mapper::toResponse)
                    .toList();
        }
        return recordRepo.findByUserIdAndCategory(user.getId(), category)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<FinancialRecordResponse> getByDateRange(User user, LocalDate start, LocalDate end) {

        if (user.getRole().name().equals("ADMIN")) {
            return recordRepo.findAll().stream()
                    .filter(r -> !r.getDate().isBefore(start) && !r.getDate().isAfter(end))
                    .map(mapper::toResponse)
                    .toList();
        }

        return recordRepo.findByUserIdAndDateBetween(user.getId(), start, end)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }





   @Override
   public Double getTotalIncome(User user) {
       if(user.getRole().name().equals("ADMIN") || user.getRole().name().equals("ANALYST")){
           return recordRepo.getGlobalTotalByType(RecordType.INCOME);
       }
       return recordRepo.getTotalByUserAndType(user.getId(), RecordType.INCOME);
   }

    @Override
    public Double getTotalExpense(User  user) {
       if(user.getRole().name().equals("ADMIN") || user.getRole().name().equals("ANALYST")){
           return recordRepo.getGlobalTotalByType(RecordType.EXPENSE);
       }
        return recordRepo.getTotalByUserAndType(user.getId(), RecordType.EXPENSE);
    }

    // ---------- TOTAL SUMMARY ----------
    @Override
    public AnalyticsResponse getTotalSummary(User user) {

        boolean isAdminOrAnalyst =
                user.getRole().name().equals("ADMIN") ||
                        user.getRole().name().equals("ANALYST");

        Double income;
        Double expense;

        if (isAdminOrAnalyst) {
            income = recordRepo.getGlobalTotalByType(RecordType.INCOME);
            expense = recordRepo.getGlobalTotalByType(RecordType.EXPENSE);
        } else {
            income = recordRepo.getTotalByUserAndType(user.getId(), RecordType.INCOME);
            expense = recordRepo.getTotalByUserAndType(user.getId(), RecordType.EXPENSE);
        }

        return buildResponse(income, expense);
    }

    // ---------- MONTHLY SUMMARY ----------
    @Override
    public AnalyticsResponse getMonthlySummary(User user, int month, int year) {

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        boolean isAdminOrAnalyst =
                user.getRole().name().equals("ADMIN") ||
                        user.getRole().name().equals("ANALYST");

        Double income;
        Double expense;

        if (isAdminOrAnalyst) {
            income = recordRepo.getMonthlyTotalGlobal(RecordType.INCOME, start, end);
            expense = recordRepo.getMonthlyTotalGlobal(RecordType.EXPENSE, start, end);
        } else {
            income = recordRepo.getMonthlyTotalByUser(user.getId(), RecordType.INCOME, start, end);
            expense = recordRepo.getMonthlyTotalByUser(user.getId(), RecordType.EXPENSE, start, end);
        }

        return buildResponse(income, expense);
    }

    // ---------- COMMON BUILDER ----------
    private AnalyticsResponse buildResponse(Double income, Double expense) {

        double inc = income != null ? income : 0;
        double exp = expense != null ? expense : 0;

        return AnalyticsResponse.builder()
                .totalIncome(inc)
                .totalExpense(exp)
                .balance(inc - exp)
                .build();
    }


}
