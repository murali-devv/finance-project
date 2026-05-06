package com.finance.app.repository;

import com.finance.app.entity.FinancialRecord;
import com.finance.app.entity.RecordType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord,Long> {

    Page<FinancialRecord> findAll(Pageable pageable);
    Page<FinancialRecord> findByUserId(Long userId, Pageable pageable);

    List<FinancialRecord> findByUserIdAndType(Long userId, RecordType type);

    List<FinancialRecord> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);

    List<FinancialRecord> findByUserIdAndCategory(Long userId, String category);



   /* @Query("SELECT COALESCE(SUM(r.amount), 0) FROM FinancialRecord r WHERE r.user.id = :userId AND r.type = 'INCOME'")
    Double getTotalIncome(Long userId);

    @Query("SELECT COALESCE(SUM(r.amount), 0) FROM FinancialRecord r WHERE r.user.id = :userId AND r.type = 'EXPENSE'")
    Double getTotalExpense(Long userId);

    @Query("""
    SELECT COALESCE(SUM(r.amount), 0)
    FROM FinancialRecord r
    WHERE r.user.id = :userId
    AND r.type = :type
    AND MONTH(r.date) = :month
    AND YEAR(r.date) = :year
""")
    Double getMonthlyTotal(Long userId, RecordType type, int month, int year);


    @Query("SELECT COALESCE(SUM(r.amount), 0) FROM FinancialRecord r WHERE r.type = 'INCOME'")
    Double getTotalIncome();

    @Query("SELECT COALESCE(SUM(r.amount), 0) FROM FinancialRecord r WHERE r.type = 'EXPENSE'")
    Double getTotalExpense();

    @Query("""
    SELECT COALESCE(SUM(r.amount), 0)
    FROM FinancialRecord r
    WHERE r.type = :type
    AND MONTH(r.date) = :month
    AND YEAR(r.date) = :year
""")
  public Double getMonthlyTotal(RecordType type, int month, int year);*/

    // USER TOTALS
    @Query("""
        SELECT COALESCE(SUM(r.amount), 0)
        FROM FinancialRecord r
        WHERE r.user.id = :userId AND r.type = :type
    """)
    Double getTotalByUserAndType(Long userId, RecordType type);

    // GLOBAL TOTALS (ADMIN/ANALYST)
    @Query("""
        SELECT COALESCE(SUM(r.amount), 0)
        FROM FinancialRecord r
        WHERE r.type = :type
    """)
    Double getGlobalTotalByType(RecordType type);

    // MONTHLY USER
    @Query("""
        SELECT COALESCE(SUM(r.amount), 0)
        FROM FinancialRecord r
        WHERE r.user.id = :userId
        AND r.type = :type
        AND r.date BETWEEN :startDate AND :endDate
    """)
    Double getMonthlyTotalByUser(Long userId, RecordType type, LocalDate startDate, LocalDate endDate);

    // MONTHLY GLOBAL
    @Query("""
        SELECT COALESCE(SUM(r.amount), 0)
        FROM FinancialRecord r
        WHERE r.type = :type
        AND r.date BETWEEN :startDate AND :endDate
    """)
    Double getMonthlyTotalGlobal(RecordType type, LocalDate startDate, LocalDate endDate);
}


