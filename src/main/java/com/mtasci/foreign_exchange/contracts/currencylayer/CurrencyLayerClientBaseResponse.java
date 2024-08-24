package com.mtasci.foreign_exchange.contracts.currencylayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyLayerClientBaseResponse {

    public boolean success;
    public CurrencyLayerClientError error;
    private String terms;
    private String privacy;

}

