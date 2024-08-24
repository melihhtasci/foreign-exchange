package com.mtasci.foreign_exchange.service;

import com.mtasci.foreign_exchange.contracts.conversion.request.ConversionRequest;
import com.mtasci.foreign_exchange.contracts.conversion.response.ConversionResponse;
import com.mtasci.foreign_exchange.dao.model.CurrencyConversion;
import com.mtasci.foreign_exchange.enums.ExchangeTypes;
import com.mtasci.foreign_exchange.exceptions.ForeignExchangeException;
import com.mtasci.foreign_exchange.utility.FileUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mtasci.foreign_exchange.constants.FileNames.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Unit test of Conversion Service methods")
@ExtendWith(MockitoExtension.class)
class ConversionServiceTest {

    @InjectMocks
    private CurrencyService currencyService;

    @Mock
    private CurrencyConversionRepositoryService currencyConversionRepositoryService;

    @Mock
    private CurrencyConverterFactory currencyConverterFactory;

    @Mock
    private CurrencyConversionService currencyConversionService;

    @Mock
    private ExchangeRateService exchangeRateService;

    @Mock
    private CurrencyLayerClientService currencyLayerClientService;

    @Test
    public void should_work_by_all_currency_converters() throws IOException, ForeignExchangeException {

        when(currencyConverterFactory.getCurrencyConverter(any(ExchangeTypes.class)))
                .thenReturn(exchangeRateService)
                .thenReturn(currencyConversionService);

        ConversionRequest request = ConversionRequest.builder().from("USD").to("TRY").amount(BigDecimal.ONE).build();
        currencyService.convert(ExchangeTypes.EXCHANGE_RATE, request);
        currencyService.convert(ExchangeTypes.EXCHANGE_RATE, request);

        verify(currencyConversionRepositoryService, times(0)).saveIfNotExistByTransactionId(any());
        verify(currencyLayerClientService, times(0)).convertCurrency(any(), any(), any(), any());
    }

    @Test
    public void shouldReturnList_whenConversionHistoryListNotEmpty() throws ForeignExchangeException, IOException {

        final List<CurrencyConversion> currencyConversions = FileUtil
                .readJsonFileAsStringToList(LIST_ENTITY_CONVERSION_HISTORY, CurrencyConversion.class);

        when(currencyConversionRepositoryService
                .getHistoryByTransactionIdAndDate(any(UUID.class),any(LocalDate.class)))
                .thenReturn(currencyConversions);

        final var response = currencyService.findHistoryByTransactionDateAndId(LocalDate.now(), UUID.randomUUID());

        assertNotNull(response);
        assertFalse(response.getConversions().isEmpty());
    }

    @Test
    public void shouldThrowError_whenConversionHistoryListNull() throws ForeignExchangeException {

        when(currencyConversionRepositoryService
                .getHistoryByTransactionIdAndDate(any(UUID.class),any(LocalDate.class)))
                .thenReturn(new ArrayList<>());

        assertThrows(ForeignExchangeException.class, () -> currencyService.findHistoryByTransactionDateAndId(LocalDate.now(), UUID.randomUUID()));

    }




}