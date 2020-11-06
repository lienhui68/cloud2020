package com.eh.cloud2020.payment8001.service.impl;

import com.eh.cloud2020.common.entity.Payment;
import com.eh.cloud2020.payment8001.dao.PaymentMapper;
import com.eh.cloud2020.payment8001.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther zzyy
 * @create 2020-02-18 10:40
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentMapper paymentDao;

    public int create(Payment payment) {
        return paymentDao.insertSelective(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.selectByPrimaryKey(id);
    }
}
