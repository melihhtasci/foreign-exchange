package com.mtasci.foreign_exchange.dao.repository;

import com.mtasci.foreign_exchange.dao.model.CurrencyConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CurrencyConversionRepository extends JpaRepository<CurrencyConversion, Long>, JpaSpecificationExecutor<CurrencyConversion> {

    List<CurrencyConversion> findByTransactionId(UUID transactionId);

    @Query("select c from CurrencyConversion c where c.transactionDate = :transactionDate ")
    List<CurrencyConversion> findByTransactionDate(@Param("transactionDate") LocalDate transactionDate);

    List<CurrencyConversion> findByTransactionDateAndTransactionId(LocalDate transactionDate, UUID transactionId);

}
