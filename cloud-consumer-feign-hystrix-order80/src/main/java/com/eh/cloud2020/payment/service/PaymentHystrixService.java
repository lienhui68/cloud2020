package com.eh.cloud2020.payment.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentOK(@PathVariable Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentTimeout(@PathVariable Integer id);
}
