package com.mtasci.foreign_exchange.contracts.currencylayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyLayerClientError {
    private int code;
    private String info;
}
