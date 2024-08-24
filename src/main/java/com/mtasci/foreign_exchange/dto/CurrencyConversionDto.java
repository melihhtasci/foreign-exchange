package com.mtasci.foreign_exchange.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.OffsetTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyConversionDto implements Serializable {

    @JsonIgnore
    private Long id;

    @Schema(name = "sourceCurrency", example = "TRY", description = "Source currency")
    private String sourceCurrency;

    @Schema(name = "sourceAmount", example = "92.12", description = "Source amount")
    private BigDecimal sourceAmount;

    @Schema(name = "targetCurrency", example = "EUR", description = "Target currency")
    private String targetCurrency;

    @Schema(name = "conversion", example = "2.36", description = "Conversion value")
    private BigDecimal conversion;

    private UUID transactionId;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate transactionDate;

    @JsonSerialize(using = OffsetTimeSerializer.class)
    @JsonDeserialize(using = OffsetTimeDeserializer.class)
    private OffsetTime transactionTime;

}
