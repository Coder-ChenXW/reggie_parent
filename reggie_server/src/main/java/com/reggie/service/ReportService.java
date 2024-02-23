package com.reggie.service;

import com.reggie.dto.GoodsSalesDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    Double getTurnover(LocalDateTime beginTime, LocalDateTime endTime);

    /**
     * 根据时间区间统计用户数量
     * @param beginTime
     * @param endTime
     * @return
     */
    Integer getUserCount(LocalDateTime beginTime, LocalDateTime endTime);

    Integer getOrderCount(LocalDateTime beginTime, LocalDateTime endTime, Integer status);

    List<GoodsSalesDTO> getSalesTop10(LocalDateTime beginTime, LocalDateTime endTime);



}
