package com.mtasci.foreign_exchange.service;

import com.mtasci.foreign_exchange.contracts.conversion.response.ConversionResponse;
import com.mtasci.foreign_exchange.dto.CurrencyConversionDto;
import com.mtasci.foreign_exchange.exceptions.ForeignExchangeException;
import com.mtasci.foreign_exchange.service.interfaces.CurrencyConverter;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

import static com.mtasci.foreign_exchange.enums.ExchangeTypes.CURRENCY_CONVERSION;

/**
 * Currency Conversion implementation of CurrencyConverter
 *
 * @author melihtasci
 * @see Service
 */
@Service
public class CurrencyConversionService implements CurrencyConverter {

    private final CurrencyLayerClientService currencyLayerClientService;
    private final CurrencyConversionRepositoryService currencyConversionRepositoryService;

    public CurrencyConversionService(CurrencyLayerClientService currencyLayerClientService,
                                     CurrencyConversionRepositoryService currencyConversionRepositoryService) {
        this.currencyLayerClientService = currencyLayerClientService;
        this.currencyConversionRepositoryService = currencyConversionRepositoryService;
    }

    @Override
    public ConversionResponse convertCurrency(String from, String to, BigDecimal amount)
            throws ForeignExchangeException {

        ConversionResponse conversionResponse = currencyLayerClientService.convertCurrency(from, to, amount, CURRENCY_CONVERSION);

        CurrencyConversionDto dto = CurrencyConversionDto.builder()
                .transactionId(conversionResponse.getTransactionId()).conversion(conversionResponse.getConversion())
                .sourceCurrency(from).targetCurrency(to).sourceAmount(amount)
                .build();
        currencyConversionRepositoryService.saveIfNotExistByTransactionId(dto);

        return conversionResponse;
    }
}
