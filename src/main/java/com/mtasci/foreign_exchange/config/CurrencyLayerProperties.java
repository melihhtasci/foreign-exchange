package com.mtasci.foreign_exchange.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("currency-layer")
@Component
@Getter
@Setter
public class CurrencyLayerProperties {

    private String url;
    private String accessKey;

}
