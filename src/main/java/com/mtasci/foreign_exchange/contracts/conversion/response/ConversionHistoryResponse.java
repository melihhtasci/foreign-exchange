package com.mtasci.foreign_exchange.contracts.conversion.response;

import com.mtasci.foreign_exchange.contracts.BaseResponse;
import com.mtasci.foreign_exchange.dto.CurrencyConversionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversionHistoryResponse extends BaseResponse {

    @Schema(name = "conversions", description = "Conversion lists", requiredMode = Schema.RequiredMode.AUTO)
    private List<CurrencyConversionDto> conversions;

}
