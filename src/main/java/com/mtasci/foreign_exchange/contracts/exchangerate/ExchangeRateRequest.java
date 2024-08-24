package com.mtasci.foreign_exchange.contracts.exchangerate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ExchangeRateRequest {

    @Schema(name = "from", example = "USD", description = "Source currency code", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "'from' parameter is required.")
    private String from;

    @Schema(name = "to", example = "EUR", description = "Target currency code", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "'to' parameter is required.")
    private String to;

}
