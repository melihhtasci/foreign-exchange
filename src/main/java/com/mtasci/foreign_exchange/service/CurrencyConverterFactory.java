package com.mtasci.foreign_exchange.service;

import com.mtasci.foreign_exchange.enums.ExchangeTypes;
import com.mtasci.foreign_exchange.service.interfaces.CurrencyConverter;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverterFactory {

    private final ExchangeRateService exchangeRateService;
    private final CurrencyConversionService currencyConversionService;

    public CurrencyConverterFactory(ExchangeRateService exchangeRateService, CurrencyConversionService currencyConversionService) {
        this.exchangeRateService = exchangeRateService;
        this.currencyConversionService = currencyConversionService;
    }

    /**
     * Returns CurrencyConverter according to Exchange Type
     *
     * @param exchangeType enum of EXCHANGE_RATE, CURRENCY_CONVERSION
     * @return CurrencyConverter according to Exchange Type
     * @throws IllegalArgumentException when invalid exchange type
     * */
    public CurrencyConverter getCurrencyConverter(ExchangeTypes exchangeType) {
        return switch (exchangeType) {
            case EXCHANGE_RATE -> exchangeRateService;
            case CURRENCY_CONVERSION -> currencyConversionService;
            //default -> throw new IllegalArgumentException("Invalid exchange type " + exchangeType);
        };
    }

}
