package com.mtasci.foreign_exchange.client;

import com.mtasci.foreign_exchange.contracts.currencylayer.CurrencyConvertResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.math.BigDecimal;

@FeignClient(name = "currency-layer", url = "#{currencyLayerProperties.url}")
public interface CurrencyLayerClient {

    @GetMapping("convert?from={from}&to={to}&amount={amount}&access_key={accessKey}")
    CurrencyConvertResponse convert(@PathVariable String from, @PathVariable String to,
                                    @PathVariable BigDecimal amount, @PathVariable String accessKey);

}
