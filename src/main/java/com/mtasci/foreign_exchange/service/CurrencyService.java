package com.mtasci.foreign_exchange.service;

import com.mtasci.foreign_exchange.contracts.ResponseBuilder;
import com.mtasci.foreign_exchange.contracts.conversion.response.ConversionHistoryResponse;
import com.mtasci.foreign_exchange.contracts.conversion.request.ConversionRequest;
import com.mtasci.foreign_exchange.contracts.conversion.response.ConversionResponse;
import com.mtasci.foreign_exchange.enums.ExchangeTypes;
import com.mtasci.foreign_exchange.exceptions.ForeignExchangeException;
import com.mtasci.foreign_exchange.service.interfaces.CurrencyConverter;
import com.mtasci.foreign_exchange.mapper.CurrencyConversionMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.UUID;
import static com.mtasci.foreign_exchange.constants.RedisCacheNameConstants.CONVERSION_HISTORY_CACHE;
import static com.mtasci.foreign_exchange.enums.ApplicationResponseCode.CODE_404;

/**
 * Contains methods about currency conversion process.
 *
 * @author melihtasci
 * @see Service
 */
@Service
public class CurrencyService {

    private final CurrencyConversionRepositoryService currencyConversionRepositoryService;
    private final CurrencyConverterFactory currencyConverterFactory;

    public CurrencyService(CurrencyConversionRepositoryService currencyConversionRepositoryService,
                           CurrencyConverterFactory currencyConverterFactory) {
        this.currencyConversionRepositoryService = currencyConversionRepositoryService;
        this.currencyConverterFactory = currencyConverterFactory;
    }


    /**
     * Get conversion information from external service and save it if exchange type is CURRENCY_CONVERSION
     * Uses CurrencyConverterFactory
     *
     * @param exchangeTypes {@link ExchangeTypes} uses for decide to save conversion information.
     * @param request {@link ConversionRequest} includes parameters which required for external service.
     * @return ConversionResponse.
     */
    public ConversionResponse convert(ExchangeTypes exchangeTypes, ConversionRequest request) throws ForeignExchangeException {
        final CurrencyConverter currencyConverter = currencyConverterFactory.getCurrencyConverter(exchangeTypes);

        return currencyConverter.convertCurrency(request.getFrom(),
                request.getTo(), request.getAmount());
    }

    /**
     * Checks currency conversions by filters and return list of currency conversion dto.
     * Caches result if transaction id not null.
     *
     * @param transactionId  {@link UUID} is identifier of transaction
     * @param transactionDate {@link LocalDate} is date of transaction
     * @throws ForeignExchangeException {@link ForeignExchangeException} when there is no data and invalid request parameters.
     * @return ConversionHistoryResponse entity
     */
    @Cacheable(value = CONVERSION_HISTORY_CACHE, key="{#transactionId}", condition = "#transactionId != null")
    public ConversionHistoryResponse findHistoryByTransactionDateAndId(LocalDate transactionDate, UUID transactionId)
            throws ForeignExchangeException {

        final var currencyConversionList = currencyConversionRepositoryService.getHistoryByTransactionIdAndDate(transactionId, transactionDate);

        if (currencyConversionList.isEmpty()) {
            throw new ForeignExchangeException(CODE_404.getDescription(), "Currency conversion history cannot be found.", CODE_404.getCode());
        }

        final var conversionDtoList = currencyConversionList.stream().map(CurrencyConversionMapper::toDto).toList();

        return ResponseBuilder.success(ConversionHistoryResponse.builder().conversions(conversionDtoList).build());
    }

}
