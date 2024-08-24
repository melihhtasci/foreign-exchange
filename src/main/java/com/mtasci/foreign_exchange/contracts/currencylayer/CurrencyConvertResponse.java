package com.mtasci.foreign_exchange.contracts.currencylayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConvertResponse extends CurrencyLayerClientBaseResponse implements Serializable {

    public double result;
}
