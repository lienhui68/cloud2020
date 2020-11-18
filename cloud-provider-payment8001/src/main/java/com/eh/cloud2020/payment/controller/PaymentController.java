package com.eh.cloud2020.payment.controller;

import com.eh.cloud2020.common.entity.CommonResult;
import com.eh.cloud2020.common.entity.Payment;
import com.eh.cloud2020.payment.service.PaymentService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;

    private final DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    public PaymentController(
            PaymentService paymentService,
            DiscoveryClient discoveryClient
    ) {
        this.paymentService = paymentService;
        this.discoveryClient = discoveryClient;
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

    @GetMapping(value = "/payment/discovery")
    public void discovery() {
        List<String> services = discoveryClient.getServices();
        services.stream().forEach(log::info);
        log.info("======分割线========");
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.stream().forEach(instance ->
                log.info(
                        "{},\t,{},\t,{},\t,{}",
                        instance.getServiceId(),
                        instance.getHost(),
                        instance.getPort(),
                        instance.getUri()
                )
        );
    }

    @SneakyThrows
    @GetMapping("/paymentTimeout")
    public String paymentTimeout() {
        TimeUnit.SECONDS.sleep(3);
        return "ok";
    }
}
