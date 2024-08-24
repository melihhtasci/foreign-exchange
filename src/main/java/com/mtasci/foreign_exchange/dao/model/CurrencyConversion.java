package com.mtasci.foreign_exchange.dao.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.OffsetTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetTimeSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.*;
import java.util.UUID;

@Entity
@Table(name = "CURRENCY_CONVERSION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyConversion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "sourceCurrency cannot be null")
    private String sourceCurrency;

    @NotNull(message = "sourceAmount cannot be null")
    private BigDecimal sourceAmount;

    @NotNull(message = "targetCurrency cannot be null")
    private String targetCurrency;

    @NotNull(message = "conversion cannot be null")
    private BigDecimal conversion;

    @NotNull(message = "transactionId cannot be null")
    private UUID transactionId;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate transactionDate;

    @JsonSerialize(using = OffsetTimeSerializer.class)
    @JsonDeserialize(using = OffsetTimeDeserializer.class)
    private OffsetTime transactionTime;

    @PrePersist
    public void prePersist() {
        transactionDate = LocalDate.now();
        transactionTime = OffsetTime.now();
    }
}
