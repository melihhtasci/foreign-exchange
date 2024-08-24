package com.mtasci.foreign_exchange.contracts.conversion.request;

import com.mtasci.foreign_exchange.contracts.exchangerate.ExchangeRateRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ConversionRequest extends ExchangeRateRequest {

    @Schema(name = "amount", example = "29.85", description = "Source currency code's amount", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "'amount' parameter is required.")
    private BigDecimal amount;

}
