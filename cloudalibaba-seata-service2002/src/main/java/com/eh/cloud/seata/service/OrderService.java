package com.eh.cloud.seata.service;

import com.eh.cloud.seata.dao.account.AccountMapper;
import com.eh.cloud.seata.dao.order.OrderMapper;
import com.eh.cloud.seata.dao.storage.StorageMapper;
import com.eh.cloud.seata.entity.Account;
import com.eh.cloud.seata.entity.Order;
import com.eh.cloud.seata.entity.Storage;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private OrderMapper orderMapper;

    @GlobalTransactional
    public void placeOrder() {
        log.info("=========>开始测试");
        log.info("用户====>用户id:{},余额:{}", "david001", "100");
        log.info("库存====>商品编号:{},商品剩余数量:{}", "product-1", "10");
        // 减库存
        Storage storage = new Storage();
        storage.setCommodityCode("product-1");
        storage.setCount(1);
        log.info("==========>开始扣减库存");
        storageMapper.decreateStorage(storage);
        // 建订单
        Order order = new Order();
        order.setUserId("david001");
        order.setCommodityCode("product-1");
        order.setCount(1);
        order.setMoney(25);
        log.info("==========>开始创建订单");
        orderMapper.createOrder(order);
        // 扣减余额
        Account account = new Account();
        account.setUserId("david001");
        account.setMoney(25);
        // 演示异常情况时打开
        int m = 1 / 0;
        log.info("==========>开始扣减余额");
        accountMapper.decreateMoney(account);
    }
}
