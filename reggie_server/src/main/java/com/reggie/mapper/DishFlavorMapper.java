package com.reggie.mapper;

import com.reggie.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {


    /**
     * @description: 批量插入口味数据
     * @author: ChenXW
     * @date: 2024/2/21 10:54
     */
    void insertBatch(List<DishFlavor> flavors);

    // 根据菜品id删除口味数据
    void deleteByDishId(Long dishId);
}
