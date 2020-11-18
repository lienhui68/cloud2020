package com.eh.cloud.seata.dao.order;

import com.eh.cloud.seata.entity.Order;
import org.apache.ibatis.annotations.Insert;

public interface OrderMapper {

    @Insert("insert into order_tbl(user_id, commodity_code, count, money) values(#{userId}, #{commodityCode}, #{count}, #{money})")
    int createOrder(Order order);
}
