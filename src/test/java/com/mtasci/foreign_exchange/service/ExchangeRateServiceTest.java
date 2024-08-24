package com.mtasci.foreign_exchange.service;

import com.mtasci.foreign_exchange.contracts.conversion.response.ConversionResponse;
import com.mtasci.foreign_exchange.enums.ExchangeTypes;
import com.mtasci.foreign_exchange.exceptions.ForeignExchangeException;
import com.mtasci.foreign_exchange.utility.FileUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;

import static com.mtasci.foreign_exchange.constants.FileNames.EXCHANGE_RATE_CONVERSION_SUCCESS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("Unit test of Exchange Rate Service methods")
@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceTest {

    @InjectMocks
    private ExchangeRateService service;

    @Mock
    private CurrencyLayerClientService currencyLayerClientService;

    @Test
    public void shouldReturn_transactionIdEmpty() throws IOException, ForeignExchangeException {
        final ConversionResponse conversionResponse = FileUtil.readJsonFileAsString(EXCHANGE_RATE_CONVERSION_SUCCESS, ConversionResponse.class);

        when(currencyLayerClientService.convertCurrency(anyString(), anyString(),
                any(BigDecimal.class), any(ExchangeTypes.class)))
                .thenReturn(conversionResponse);

        final var response = service.convertCurrency("USD", "TRY", BigDecimal.valueOf(12));

        assertNull(response.getTransactionId());
    }

}