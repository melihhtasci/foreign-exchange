package com.mtasci.foreign_exchange.contracts.conversion.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversionHistoryRequest {


    @Schema(name = "transactionDate", example = "2024-10-18", description = "Transaction date", requiredMode = Schema.RequiredMode.AUTO)
    private LocalDate transactionDate;

    @Schema(name = "transactionId", example = "asd5asd6-ddaq395-w1edaas-sadawd", description = "Transaction id", requiredMode = Schema.RequiredMode.AUTO)
    private UUID transactionId;

}
