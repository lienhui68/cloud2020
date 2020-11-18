package com.eh.cloud2020.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderConsumerController {

    private final static String PAYMENT_URL = "http://cloud-payment-consul-service";

    private final RestTemplate restTemplate;

    public OrderConsumerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/order/payment")
    public String getPaymentById() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/consul", String.class);
    }
}
