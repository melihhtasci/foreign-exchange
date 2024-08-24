package com.mtasci.foreign_exchange.service.interfaces;

import com.mtasci.foreign_exchange.contracts.conversion.response.ConversionResponse;
import com.mtasci.foreign_exchange.exceptions.ForeignExchangeException;

import java.math.BigDecimal;

public interface CurrencyConverter {

    ConversionResponse convertCurrency(String from, String to, BigDecimal amount) throws ForeignExchangeException;
}
