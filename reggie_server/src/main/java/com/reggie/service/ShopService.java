package com.reggie.service;

import io.swagger.models.auth.In;

public interface ShopService {

    // 设置店铺的营业状态
    void setStatus(Integer status);

    // 查询店铺的运营状态
    Integer getStatus();

}
