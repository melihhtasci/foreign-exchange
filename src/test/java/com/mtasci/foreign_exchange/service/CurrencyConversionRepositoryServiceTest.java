package com.mtasci.foreign_exchange.service;

import com.mtasci.foreign_exchange.contracts.conversion.request.ConversionRequest;
import com.mtasci.foreign_exchange.contracts.conversion.response.ConversionResponse;
import com.mtasci.foreign_exchange.dao.model.CurrencyConversion;
import com.mtasci.foreign_exchange.dto.CurrencyConversionDto;
import com.mtasci.foreign_exchange.exceptions.ForeignExchangeException;
import com.mtasci.foreign_exchange.utility.FileUtil;
import com.mtasci.foreign_exchange.dao.repository.CurrencyConversionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.mtasci.foreign_exchange.constants.FileNames.CURRENCY_EXCHANGE_CONVERSION_SUCCESS;
import static com.mtasci.foreign_exchange.constants.FileNames.LIST_ENTITY_CONVERSION_HISTORY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Unit test of Currency Conversion Repository Service methods")
@ExtendWith(MockitoExtension.class)
class CurrencyConversionRepositoryServiceTest {

    @InjectMocks
    private CurrencyConversionRepositoryService service;

    @Mock
    private CurrencyConversionRepository currencyConversionRepository;

    @Test
    public void shouldNotSaveCurrencyConversion_whenAlreadyExists() throws IOException {

        final List<CurrencyConversion> currencyConversions = FileUtil.readJsonFileAsStringToList(LIST_ENTITY_CONVERSION_HISTORY, CurrencyConversion.class);
        final ConversionResponse conversionResponse = FileUtil.readJsonFileAsString(CURRENCY_EXCHANGE_CONVERSION_SUCCESS, ConversionResponse.class);
        ConversionRequest request = ConversionRequest.builder().from("USD").to("TRY").amount(BigDecimal.ONE).build();

        when(currencyConversionRepository.findByTransactionId(any(UUID.class))).thenReturn(currencyConversions);

        final var dto = CurrencyConversionDto.builder().conversion(conversionResponse.getConversion())
                .transactionId(conversionResponse.getTransactionId())
                .targetCurrency(request.getTo())
                .sourceCurrency(request.getFrom())
                .sourceAmount(request.getAmount())
                .build();
        final var response = service.saveIfNotExistByTransactionId(dto);

        assertNotNull(response);
        verify(currencyConversionRepository,times(0)).save(any());

    }

    @Test
    public void shouldSaveCurrencyConversion_whenDoesntExists() throws IOException {

        final CurrencyConversion currencyConversion = FileUtil.readJsonFileAsStringToList(LIST_ENTITY_CONVERSION_HISTORY, CurrencyConversion.class).get(0);
        final ConversionResponse conversionResponse = FileUtil.readJsonFileAsString(CURRENCY_EXCHANGE_CONVERSION_SUCCESS, ConversionResponse.class);
        ConversionRequest request = ConversionRequest.builder().from("USD").to("TRY").amount(BigDecimal.ONE).build();

        when(currencyConversionRepository.findByTransactionId(any(UUID.class))).thenReturn(new ArrayList<>());

        when(currencyConversionRepository.save(any(CurrencyConversion.class))).thenReturn(currencyConversion);

        final var dto = CurrencyConversionDto.builder().conversion(conversionResponse.getConversion())
                .transactionId(conversionResponse.getTransactionId())
                .targetCurrency(request.getTo())
                .sourceCurrency(request.getFrom())
                .sourceAmount(request.getAmount())
                .build();
        final var response = service.saveIfNotExistByTransactionId(dto);

        assertNotNull(response);
        verify(currencyConversionRepository,times(1)).save(any());
    }

    @Test
    public void shouldThrowError_whenParametersAreNull() throws IOException {

        assertThrows(ForeignExchangeException.class, () ->
                service.getHistoryByTransactionIdAndDate(null, null)
        );
    }

    @Test
    public void shouldWork_findByTransactionDateAndTransactionId_byGivenParameters() throws IOException, ForeignExchangeException {

        final List<CurrencyConversion> currencyConversions = FileUtil.readJsonFileAsStringToList(LIST_ENTITY_CONVERSION_HISTORY, CurrencyConversion.class);

        when(currencyConversionRepository
                .findByTransactionDateAndTransactionId(any(LocalDate.class), any(UUID.class)))
                .thenReturn(currencyConversions);

        final var response = service.getHistoryByTransactionIdAndDate(UUID.randomUUID(), LocalDate.now());

        assertNotNull(response);
        verify(currencyConversionRepository,times(1)).findByTransactionDateAndTransactionId(any(), any());
    }

    @Test
    public void shouldWork_findByTransactionId_byGivenParameters() throws IOException, ForeignExchangeException {

        final List<CurrencyConversion> currencyConversions = FileUtil.readJsonFileAsStringToList(LIST_ENTITY_CONVERSION_HISTORY, CurrencyConversion.class);

        when(currencyConversionRepository.findByTransactionId(any(UUID.class))).thenReturn(currencyConversions);

        final var response = service.getHistoryByTransactionIdAndDate(UUID.randomUUID(), null);

        assertNotNull(response);
        verify(currencyConversionRepository,times(1)).findByTransactionId(any());
    }

    @Test
    public void shouldWork_findByTransactionDate_byGivenParameters() throws IOException, ForeignExchangeException {

        final List<CurrencyConversion> currencyConversions = FileUtil.readJsonFileAsStringToList(LIST_ENTITY_CONVERSION_HISTORY, CurrencyConversion.class);

        when(currencyConversionRepository.findByTransactionDate(any(LocalDate.class))).thenReturn(currencyConversions);

        final var response = service.getHistoryByTransactionIdAndDate(null, LocalDate.now());

        assertNotNull(response);
        verify(currencyConversionRepository,times(1)).findByTransactionDate(any());
    }

}