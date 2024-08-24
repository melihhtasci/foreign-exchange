package com.mtasci.foreign_exchange.contracts.conversion.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mtasci.foreign_exchange.contracts.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversionResponse extends BaseResponse implements Serializable {

    @Schema(name = "conversion", example = "20.61", description = "Amount of target currency")
    //@JsonDeserialize(using = BigDecimalArrayDeserializer.class)
    private BigDecimal conversion;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID transactionId;

}
