package com.eh.cloud2020.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * payment
 * @author 
 */
@Data
public class Payment implements Serializable {
    /**
     * ID
     */
    private Long id;

    private String serial;

    private static final long serialVersionUID = 1L;
}