package com.eh.cloud.seata.dao.storage;

import com.eh.cloud.seata.entity.Storage;
import org.apache.ibatis.annotations.Update;

public interface StorageMapper {

    @Update("update storage_tbl set count = count - #{count} where commodity_code = #{commodityCode}")
    int decreateStorage(Storage storage);

}