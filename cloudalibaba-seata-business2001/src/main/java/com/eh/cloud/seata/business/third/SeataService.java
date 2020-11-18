package com.eh.cloud.seata.business.third;

import com.eh.cloud2020.common.entity.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient("cloudalibaba-seata-service2002")
public interface SeataService {

    @PostMapping("/account/decrease")
    CommonResult<String> decreateAccount(Map<String, String> request);

    @PostMapping("/order/create")
    CommonResult<String> createOrder(Map<String, String> request);
}
