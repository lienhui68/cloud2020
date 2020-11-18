package com.eh.cloud2020.order.controller;

import com.eh.cloud2020.common.entity.CommonResult;
import com.eh.cloud2020.common.entity.Payment;
import com.eh.cloud2020.order.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        log.info("开始创建订单：{}", payment);
        try {
            Assert.notNull(payment.getSerial(), "参数serial不能为空");
            paymentService.create(payment);
            return CommonResult.success(payment, "创建订单成功，提供服务端口号：" + serverPort);
        } catch (Exception e) {
            log.error("创建订单出现异常", e);
            return CommonResult.error(444, "创建订单失败");
        }
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            log.info("hello");
            return CommonResult.success(payment, "查询订单成功，提供服务端口号：" + serverPort);
        } else {
            return CommonResult.error(444, "没有对应记录, 查询ID: " + id);
        }
    }
}
