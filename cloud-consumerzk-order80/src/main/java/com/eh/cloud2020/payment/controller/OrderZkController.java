package com.eh.cloud2020.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderZkController {

    private final static String PAYMENT_URL = "http://cloud-payment-service";

    private final RestTemplate restTemplate;

    public OrderZkController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/order/paymentZK")
    public String getPaymentById() {
        return restTemplate.getForObject(PAYMENT_URL + "/paymentZK/", String.class);
    }
}
