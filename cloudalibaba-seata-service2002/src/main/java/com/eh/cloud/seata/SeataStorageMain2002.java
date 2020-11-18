package com.eh.cloud.seata;

import com.eh.cloud.seata.dao.account.AccountMapper;
import com.eh.cloud.seata.dao.order.OrderMapper;
import com.eh.cloud.seata.dao.storage.StorageMapper;
import com.eh.cloud.seata.entity.Account;
import com.eh.cloud.seata.entity.Order;
import com.eh.cloud.seata.entity.Storage;
import com.eh.cloud.seata.service.OrderService;
import com.eh.cloud2020.common.entity.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SeataStorageMain2002 {
    public static void main(String[] args) {
        SpringApplication.run(SeataStorageMain2002.class, args);
    }

    @RestController
    class StorageController {

        @Autowired
        private StorageMapper storageMapper;

        @PostMapping("/storage/decrease")
        public CommonResult<String> decreateStorage(@RequestBody Map<String, String> request) {
            try {
                Storage storage = new Storage();
                storage.setCommodityCode(request.get("commodityCode"));
                storage.setCount(Integer.valueOf(request.get("count")));
                int i = storageMapper.decreateStorage(storage);
                return CommonResult.success("成功更新：" + i);
            } catch (Exception e) {
                return CommonResult.error(80001, "库存服务出错");
            }

        }
    }

    @RestController
    class OrderController {

        @Autowired
        private OrderMapper orderMapper;
        @Autowired
        private AccountMapper accountMapper;

        @PostMapping("/order/create")
        public CommonResult<String> createOrder(@RequestBody Map<String, String> request) {
            try {
                // 创建订单
                Order order = new Order();
                order.setCommodityCode(request.get("userId"));
                order.setCommodityCode(request.get("commodityCode"));
                order.setCount(Integer.valueOf(request.get("count")));
                order.setMoney(Integer.valueOf(request.get("money")));
                int i = orderMapper.createOrder(order);
                // 扣减余额
                Account account = new Account();
                account.setUserId(request.get("userId"));
                account.setMoney(Integer.valueOf(request.get("money")));
                // 演示异常情况时打开
                int m = 1 / 0;
                int j = accountMapper.decreateMoney(account);
                return CommonResult.success("成功创建订单并扣减余额：" + (i + j));
            } catch (Exception e) {
                return CommonResult.error(80002, "订单服务出错");
            }
        }

        @Autowired
        private OrderService orderService;

        @GetMapping("/test")
        public void test() {
            orderService.placeOrder();
        }

        @GetMapping("/t1")
        public void t1() {
            Order order = new Order();
            order.setUserId("david002");
            orderMapper.createOrder(order);
        }
    }

}
