package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.dto.GoodsSalesDTO;
import com.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order
     */
    void insert(Orders order);

    /**
     * 根据订单号和用户id查询订单
     * @param orderNumber
     * @param userId
     * @return
     */
    Orders getByNumber(String orderNumber, Long userId);

    /**
     * 修改订单信息
     * @param orders
     */
    void updateOrdersById(Orders orders);

    /**
     * 分页条件查询并按下单时间排序
     *
     * @param map
     * @return
     */
    Page<Orders> pageQuerySortByOrderTime(Map map);

    /**
     * 根据id查询订单
     * @param id
     * @return
     */
    Orders getById(Long id);



    List<Orders> getByStatusAndOrdertimeLT(Integer status, LocalDateTime orderTime);

    /**
     * 根据状态统计订单数量
     * @param status
     * @return
     */
    Integer countStatus(Integer status);

    Double sumByMap(Map map);

    Integer countByMap(Map map);

    List<GoodsSalesDTO> getSalesTop10(LocalDateTime begin, LocalDateTime end);

}
