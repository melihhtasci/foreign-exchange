package com.mtasci.foreign_exchange.service;

import com.mtasci.foreign_exchange.client.CurrencyLayerClient;
import com.mtasci.foreign_exchange.contracts.ResponseBuilder;
import com.mtasci.foreign_exchange.config.CurrencyLayerProperties;
import com.mtasci.foreign_exchange.contracts.currencylayer.CurrencyConvertResponse;
import com.mtasci.foreign_exchange.contracts.conversion.response.ConversionResponse;
import com.mtasci.foreign_exchange.enums.ExchangeTypes;
import com.mtasci.foreign_exchange.exceptions.ForeignExchangeException;
import com.mtasci.foreign_exchange.enums.CurrentLayerResponseCode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

import static com.mtasci.foreign_exchange.constants.RedisCacheNameConstants.CURRENCY_CACHE;
import static com.mtasci.foreign_exchange.enums.ApplicationResponseCode.CODE_1099;

@Service
public class CurrencyLayerClientService {

    private final CurrencyLayerClient currencyLayerClient;
    private final CurrencyLayerProperties currencyLayerProperties;

    public CurrencyLayerClientService(CurrencyLayerClient currencyLayerClient,
                                      CurrencyLayerProperties currencyLayerProperties) {
        this.currencyLayerClient = currencyLayerClient;
        this.currencyLayerProperties = currencyLayerProperties;
    }

    /**
     * Get currency conversion by from, to and amount parameters.
     * Caches external service result by CURRENCY_CACHE::#from,#to,#amount,#exchangeTypes
     *
     * @param from  {@link String} is source currency code
     * @param to  {@link String} is target currency code
     * @param amount  {@link BigDecimal} is value of source currency's
     * @param exchangeTypes  {@link ExchangeTypes} is indicates that is EXCHANGE_RATE or CURRENCY_CONVERSION
     *
     * @throws ForeignExchangeException {@link ForeignExchangeException} when unexpected situation happened or
     * external service returned code except 200 response code.
     * @return ConversionHistoryResponse entity
     */
    @Cacheable(value = CURRENCY_CACHE, key="{#from, #to, #amount, #exchangeTypes}")
    public ConversionResponse convertCurrency(String from, String to, BigDecimal amount, ExchangeTypes exchangeTypes) throws ForeignExchangeException {

        try {
            final CurrencyConvertResponse response = currencyLayerClient.convert(from, to, amount, currencyLayerProperties.getAccessKey());

            if (!response.isSuccess()) {
                final var responseCode = CurrentLayerResponseCode.findByCode(response.getError().getCode());
                throw new ForeignExchangeException("An error has occurred.", responseCode.getDescription(), responseCode.getCode());
            }

            final UUID transactionId = ExchangeTypes.CURRENCY_CONVERSION.equals(exchangeTypes) ? UUID.randomUUID() : null;

            return ResponseBuilder.success(ConversionResponse.builder()
                    .conversion(BigDecimal.valueOf(response.getResult()))
                    .transactionId(transactionId)
                    .build());

        } catch (ForeignExchangeException e) {
            throw e;
        } catch (Exception e) {
            throw new ForeignExchangeException(e.getMessage(), e.getCause().getMessage(), CODE_1099.getCode());
        }

    }

    @Cacheable(value = CURRENCY_CACHE, key="{#from, #to, #amount, #exchangeTypes}")
    public ConversionResponse convertCurrency2(String from, String to, BigDecimal amount, ExchangeTypes exchangeTypes) throws ForeignExchangeException {

        try {
            final CurrencyConvertResponse response = currencyLayerClient.convert(from, to, amount, currencyLayerProperties.getAccessKey());

            if (!response.isSuccess()) {
                final var responseCode = CurrentLayerResponseCode.findByCode(response.getError().getCode());
                throw new ForeignExchangeException("An error has occurred.", responseCode.getDescription(), responseCode.getCode());
            }

            final UUID transactionId = ExchangeTypes.CURRENCY_CONVERSION.equals(exchangeTypes) ? UUID.randomUUID() : null;

            return ResponseBuilder.success(ConversionResponse.builder()
                    .conversion(BigDecimal.valueOf(response.getResult()))
                    .transactionId(transactionId)
                    .build());

        } catch (ForeignExchangeException e) {
            throw e;
        } catch (Exception e) {
            throw new ForeignExchangeException(e.getMessage(), e.getCause().getMessage(), CODE_1099.getCode());
        }

    }

}
