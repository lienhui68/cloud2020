package com.eh.cloud.seata.dao.account;

import com.eh.cloud.seata.entity.Account;
import org.apache.ibatis.annotations.Update;

public interface AccountMapper {

    @Update("update account_tbl set money = money - #{money} where user_id = #{userId}")
    int decreateMoney(Account account);
}
