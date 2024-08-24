package com.mtasci.foreign_exchange.service;

import com.mtasci.foreign_exchange.dao.model.CurrencyConversion;
import com.mtasci.foreign_exchange.dto.CurrencyConversionDto;
import com.mtasci.foreign_exchange.exceptions.ForeignExchangeException;
import com.mtasci.foreign_exchange.dao.repository.CurrencyConversionRepository;
import com.mtasci.foreign_exchange.mapper.CurrencyConversionMapper;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.mtasci.foreign_exchange.enums.ApplicationResponseCode.CODE_400;


/**
 * For now, includes database logics of Currency Conversion table.
 *
 * @author melihtasci
 * @see Service
 */
@Service
public class CurrencyConversionRepositoryService {

    private final CurrencyConversionRepository currencyConversionRepository;

    public CurrencyConversionRepositoryService(CurrencyConversionRepository currencyConversionRepository) {
        this.currencyConversionRepository = currencyConversionRepository;
    }

    /**
     * Saves currency conversion if did not save before by same transaction id.
     * So checks table by transaction id.
     * Use when data is cached, you are not sure if it has been added to the database.
     *
     * @param currencyConversionDto the {@link CurrencyConversionDto}
     * @return CurrencyConversion the {@link CurrencyConversion}
     */
    public CurrencyConversion saveIfNotExistByTransactionId(CurrencyConversionDto currencyConversionDto) {
        final var currencyConversionList = currencyConversionRepository.findByTransactionId(currencyConversionDto.getTransactionId());

        if (!currencyConversionList.isEmpty())
            return currencyConversionList.get(0);

        return save(currencyConversionDto);
    }

    /**
     * Saves currency conversion.
     *
     * @param dto the {@link CurrencyConversionDto}
     * @return CurrencyConversion the {@link CurrencyConversion}
     */
    public CurrencyConversion save(CurrencyConversionDto dto) {
        final var entity = CurrencyConversionMapper.toEntity(dto);
        return currencyConversionRepository.save(entity);
    }

    /**
     * Checks currency conversions by filters and return list of currency conversion.
     *
     * @param transactionId the {@link UUID} is identifier of transaction
     * @param transactionDate the {@link LocalDate} is date of transaction
     * @throws ForeignExchangeException {@link ForeignExchangeException} when all parameters are null
     * @return List<CurrencyConversion> entityList
     */
    public List<CurrencyConversion> getHistoryByTransactionIdAndDate(UUID transactionId, LocalDate transactionDate)
            throws ForeignExchangeException {

        if (Objects.isNull(transactionDate) && Objects.isNull(transactionId)) {
            throw new ForeignExchangeException(CODE_400.getDescription(), "Invalid transaction date or transaction id.", CODE_400.getCode());
        }

        if (Objects.nonNull(transactionId) && Objects.nonNull(transactionDate)) {
            return currencyConversionRepository.findByTransactionDateAndTransactionId(transactionDate, transactionId);
        } else if (Objects.nonNull(transactionId)) {
            return currencyConversionRepository.findByTransactionId(transactionId);
        }
        return currencyConversionRepository.findByTransactionDate(transactionDate);
    }

}
