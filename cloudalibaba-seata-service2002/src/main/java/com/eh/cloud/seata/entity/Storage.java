package com.eh.cloud.seata.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Storage implements Serializable {
    private Integer id;
    private String commodityCode;
    private Integer count;
}