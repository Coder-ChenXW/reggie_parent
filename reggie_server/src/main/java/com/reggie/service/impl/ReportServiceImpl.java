package com.reggie.service.impl;

import com.reggie.entity.Orders;
import com.reggie.mapper.OrderMapper;
import com.reggie.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ChenXW
 * @Date:2024/2/23 10:05
 * @Description:
 **/

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Double getTurnover(LocalDateTime beginTime, LocalDateTime endTime) {

        Map map = new HashMap();
        map.put("status", Orders.COMPLETED);
        map.put("begin", beginTime);
        map.put("end", endTime);
        Double turnover = orderMapper.sumByMap(map);

        return turnover == null ? 0.0 : turnover;
    }
}
