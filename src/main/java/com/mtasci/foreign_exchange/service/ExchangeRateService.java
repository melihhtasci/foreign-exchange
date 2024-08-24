package com.mtasci.foreign_exchange.service;

import com.mtasci.foreign_exchange.contracts.conversion.response.ConversionResponse;
import com.mtasci.foreign_exchange.exceptions.ForeignExchangeException;
import com.mtasci.foreign_exchange.service.interfaces.CurrencyConverter;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import static com.mtasci.foreign_exchange.enums.ExchangeTypes.EXCHANGE_RATE;

/**
 * Exchange rate implementation of CurrencyConverter
 *
 * @author melihtasci
 * @see Service
 */
@Service
public class ExchangeRateService implements CurrencyConverter {

    private final CurrencyLayerClientService currencyLayerClientService;

    public ExchangeRateService(CurrencyLayerClientService currencyLayerClientService) {
        this.currencyLayerClientService = currencyLayerClientService;
    }

    @Override
    public ConversionResponse convertCurrency(String from, String to, BigDecimal amount) throws ForeignExchangeException {

        return currencyLayerClientService.convertCurrency(from, to, BigDecimal.ONE, EXCHANGE_RATE);

    }
}
