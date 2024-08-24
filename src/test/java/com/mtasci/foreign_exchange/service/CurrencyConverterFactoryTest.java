package com.mtasci.foreign_exchange.service;

import com.mtasci.foreign_exchange.enums.ExchangeTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit test of CurrencyConverterFactory methods")
@ExtendWith(MockitoExtension.class)
class CurrencyConverterFactoryTest {

    @InjectMocks
    private CurrencyConverterFactory factory;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Mock
    private CurrencyConversionService currencyConversionService;


    @Test
    void should_return_exchangeRateService() {

        final var converter = factory.getCurrencyConverter(ExchangeTypes.EXCHANGE_RATE);
        assertEquals(exchangeRateService, converter);

    }

    @Test
    void should_return_currencyConversionService() {

        final var converter = factory.getCurrencyConverter(ExchangeTypes.CURRENCY_CONVERSION);
        assertEquals(currencyConversionService, converter);

    }

    @Test
    void should_throw_exception_when_exchangeRateService_is_null() {
        assertThrows(IllegalArgumentException.class, () -> factory.getCurrencyConverter(ExchangeTypes.valueOf("INVALID")));
    }

}