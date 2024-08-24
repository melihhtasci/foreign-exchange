package com.mtasci.foreign_exchange.controller;

import com.mtasci.foreign_exchange.contracts.conversion.response.ConversionHistoryResponse;
import com.mtasci.foreign_exchange.contracts.conversion.request.ConversionRequest;
import com.mtasci.foreign_exchange.contracts.conversion.response.ConversionResponse;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

import static com.mtasci.foreign_exchange.constants.ApiExampleResponses.*;

@RestController
@RequestMapping("api/v1/conversion")
@Tag(name = "Conversion API")
public class ConversionController {

    private final CurrencyService currencyService;

    public ConversionController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @Operation(summary = "Converts currency between 2 currencies", description = "Gets source currency and amount, make currency conversion to target currency")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = CURRENCY_NOT_FOUND))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = CURRENCY_BAD_REQUEST)))
    })
    @PostMapping("/convert")
    public ResponseEntity<ConversionResponse> convert(@Parameter(name = "Conversion Request", description = "Conversion request object between two currencies")
                                                          @Valid @RequestBody ConversionRequest request) throws ForeignExchangeException {

        final var response = currencyService.convert(ExchangeTypes.CURRENCY_CONVERSION, request);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Return currency conversion by transaction date or transaction id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful"),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = HISTORY_NOT_FOUND))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", examples = @ExampleObject(value = HISTORY_BAD_REQUEST)))
    })
    @GetMapping("/history/{transactionDate}/transactionId/{transactionId}")
    public ResponseEntity<ConversionHistoryResponse> conversionHistory(
            @Parameter(name = "transactionDate", description = "Date of conversion")
            @RequestParam(value = "transactionDate", required = false)
            LocalDate transactionDate,
            @Parameter(name = "transactionId", description = "Unique identifier of conversion")
            @RequestParam(value = "transactionId", required = false)
            UUID transactionId
                                                             ) throws ForeignExchangeException {
        final var response = currencyService.findHistoryByTransactionDateAndId(transactionDate, transactionId);
        return ResponseEntity.ok(response);
    }

}
