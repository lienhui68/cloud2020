package com.eh.cloud2020.payment;

import feign.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
