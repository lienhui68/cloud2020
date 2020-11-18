package com.eh.cloud2020.order.controller;

import com.eh.cloud2020.common.entity.CommonResult;
import com.eh.cloud2020.common.entity.Payment;
import com.eh.cloud2020.order.config.MyLoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    //    private final static String PAYMENT_URL = "http://localhost:8001";
    private final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    private final RestTemplate restTemplate;
    private final MyLoadBalancer myLoadBalancer;
    private final DiscoveryClient discoveryClient;

    public OrderController(
            RestTemplate restTemplate,
            MyLoadBalancer myLoadBalancer,
            DiscoveryClient discoveryClient
    ) {
        this.restTemplate = restTemplate;
        this.myLoadBalancer = myLoadBalancer;
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/order/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        ServiceInstance serviceInstance = myLoadBalancer.getInstance(discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE"));
        if (serviceInstance == null) {
            return null;
        }
        return restTemplate.getForObject(serviceInstance.getUri() + "/payment/" + id, CommonResult.class, id);
    }

    @PostMapping("/order/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }
}
