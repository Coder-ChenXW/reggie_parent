package com.reggie.service.impl;

import com.reggie.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: ChenXW
 * @Date:2024/2/21 14:09
 * @Description: 店铺状态操作服务
 **/

@Service
@Slf4j
public class ShopServiceImpl implements ShopService {

    public static final String KEY = "SHOP:STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void setStatus(Integer status) {
        redisTemplate.opsForValue().set(KEY, status);
        log.info("设置营业状态为:{}", status == 1 ? "营业中" : "打烊中");
    }

    @Override
    public Integer getStatus() {
        return (Integer) redisTemplate.opsForValue().get(KEY);
    }
}
