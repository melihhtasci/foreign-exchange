package com.mtasci.foreign_exchange.controller;

import com.mtasci.foreign_exchange.contracts.conversion.request.ConversionRequest;
import com.mtasci.foreign_exchange.contracts.conversion.response.ConversionResponse;
import com.mtasci.foreign_exchange.contracts.exchangerate.ExchangeRateRequest;
import com.mtasci.foreign_exchange.enums.ExchangeTypes;
import com.mtasci.foreign_exchange.exceptions.ForeignExchangeException;
import com.mtasci.foreign_exchange.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static com.mtasci.foreign_exchange.constants.ApiExampleResponses.*;

@RestController
@RequestMapping("api/v1/exchange-rate")
@Tag(name = "Exchange Rate API")
public class ExchangeRateController {

    private final CurrencyService currencyService;

    public ExchangeRateController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @Operation(summary = "Return the current exchange rate between the two currencies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = EXCHANGE_RATE_SUCCESS))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = CURRENCY_NOT_FOUND))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = CURRENCY_BAD_REQUEST)))
    })
    @PostMapping("/convert")
    public ResponseEntity<ConversionResponse> convert(@Parameter(name = "Exchange Rate Request", description = "Conversion request object between two currencies")
                                                      @Valid @RequestBody ExchangeRateRequest request) throws ForeignExchangeException {

        final var conversionRequest = ConversionRequest.builder()
                .from(request.getFrom()).to(request.getTo()).amount(BigDecimal.ONE).build();

        final var response = currencyService.convert(ExchangeTypes.EXCHANGE_RATE, conversionRequest);
        return ResponseEntity.ok(response);
    }
}
