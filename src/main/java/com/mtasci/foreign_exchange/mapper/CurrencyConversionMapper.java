package com.mtasci.foreign_exchange.mapper;

import com.mtasci.foreign_exchange.dao.model.CurrencyConversion;
import com.mtasci.foreign_exchange.dto.CurrencyConversionDto;

public class CurrencyConversionMapper extends BaseMapper<CurrencyConversionDto, CurrencyConversion> {

    public static CurrencyConversion toEntity(CurrencyConversionDto dto) {
        return CurrencyConversion.builder()
                .sourceCurrency(dto.getSourceCurrency()).sourceAmount(dto.getSourceAmount())
                .targetCurrency(dto.getTargetCurrency()).conversion(dto.getConversion())
                .transactionDate(dto.getTransactionDate()).transactionTime(dto.getTransactionTime())
                .transactionId(dto.getTransactionId()).id(dto.getId()).build();
    }

    public static CurrencyConversionDto toDto(CurrencyConversion entity) {
        return CurrencyConversionDto.builder()
                .sourceCurrency(entity.getSourceCurrency()).sourceAmount(entity.getSourceAmount())
                .targetCurrency(entity.getTargetCurrency()).conversion(entity.getConversion())
                .transactionDate(entity.getTransactionDate()).transactionTime(entity.getTransactionTime())
                .transactionId(entity.getTransactionId()).id(entity.getId()).build();
    }
    
}
