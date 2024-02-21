package com.reggie.service;

import com.reggie.dto.DishDTO;
import com.reggie.dto.DishPageQueryDTO;
import com.reggie.result.PageResult;
import com.reggie.vo.DishVO;

import java.util.List;

public interface DishService {

    // 新增菜品
    void saveWithFlavor(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    // 菜品的批量删除
    void deleteBatch(List<Long> ids);

    // 根据id查询菜品和关联的口味数据
    DishVO getByIdWithFlavor(Long id);

    // 根据id修改菜品和关联的口味
    void updateWithFlavor(DishDTO dishDTO);
}
