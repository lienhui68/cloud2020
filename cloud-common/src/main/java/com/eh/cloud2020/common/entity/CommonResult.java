package com.eh.cloud2020.common.entity;

import lombok.Data;

@Data
public class CommonResult<T> {
    private Integer code;
    private String desc;
    private T data;

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setCode(200);
        commonResult.setDesc("处理成功");
        commonResult.setData(data);
        return commonResult;
    }

    public static <T> CommonResult<T> success(T data, String desc) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setCode(200);
        commonResult.setDesc(desc);
        commonResult.setData(data);
        return commonResult;
    }

    public static <T> CommonResult<T> error(Integer code, String desc) {
        CommonResult<T> commonResult = new CommonResult<>();
        commonResult.setCode(code);
        commonResult.setDesc(desc);
        return commonResult;
    }


}
