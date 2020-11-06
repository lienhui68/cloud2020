package com.eh.cloud2020.payment8001.service;

import com.eh.cloud2020.common.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @auther zzyy
 * @create 2020-02-18 10:40
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
