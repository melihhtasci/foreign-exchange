package com.mtasci.foreign_exchange.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("redis")
@Component
@Getter
@Setter
public class RedisProperties {
    private String host;
    private int port;
    private int ttl;

}
