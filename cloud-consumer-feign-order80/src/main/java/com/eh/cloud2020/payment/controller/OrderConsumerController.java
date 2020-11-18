package com.eh.cloud2020.order.controller;

import com.eh.cloud2020.common.entity.CommonResult;
import com.eh.cloud2020.common.entity.Payment;
import com.eh.cloud2020.order.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderConsumerController {


    private final PaymentFeignService paymentFeignService;


    @Value("${server.port}")
    private String serverPort;

    public OrderConsumerController(PaymentFeignService paymentFeignService) {
        this.paymentFeignService = paymentFeignService;
    }

    @GetMapping("/order/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }
}
