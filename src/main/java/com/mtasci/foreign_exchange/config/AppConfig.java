package com.mtasci.foreign_exchange.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableFeignClients(basePackages = "com.mtasci.foreign_exchange.client")
@EnableAspectJAutoProxy
public class AppConfig {

}
