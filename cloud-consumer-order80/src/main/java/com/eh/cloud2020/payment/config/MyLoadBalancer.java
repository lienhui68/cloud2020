package com.eh.cloud2020.order.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalancer {
    // 下一个服务器地址下标
    private AtomicInteger nextServerCyclicCounter = new AtomicInteger(0);

    public ServiceInstance getInstance(List<ServiceInstance> instances) {
        if (instances == null || instances.size() < 1) {
            return null;
        }
        return instances.get(nextServerCyclicCounter.getAndIncrement() % instances.size());
    }
}
