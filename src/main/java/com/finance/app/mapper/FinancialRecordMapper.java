package com.finance.app.mapper;

import com.finance.app.dto.FinancialRecordRequest;
import com.finance.app.dto.FinancialRecordResponse;
import com.finance.app.entity.FinancialRecord;
import com.finance.app.entity.RecordType;
import org.springframework.stereotype.Component;

@Component
public class FinancialRecordMapper {

    public FinancialRecord toEntity(FinancialRecordRequest dto) {
        return FinancialRecord.builder()
                .title(dto.getTitle())
                .amount(dto.getAmount())
                .type(RecordType.valueOf(dto.getType()))
                .category(dto.getCategory())
                .date(dto.getDate())
                .build();
    }

    public FinancialRecordResponse toResponse(FinancialRecord entity) {

        return FinancialRecordResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .amount(entity.getAmount())
                .type(entity.getType().name())
                .category(entity.getCategory())
                .date(entity.getDate())
                .build();
    }

}