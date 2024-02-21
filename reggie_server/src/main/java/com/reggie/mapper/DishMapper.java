package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
import com.reggie.dto.CategoryPageQueryDTO;
import com.reggie.dto.DishPageQueryDTO;
import com.reggie.entity.Category;
import com.reggie.entity.Dish;
import com.reggie.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    Integer countByCategoryId(Long categoryId);

    /**
     * @description: 插入菜品数据
     * @author: ChenXW
     * @date: 2024/2/21 10:39
     */
    @AutoFill(type = AutoFillConstant.INSERT)
    void insert(Dish dish);

    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    // 根据主键查询菜品数据
    Dish getById(Long id);

    // 根据主键删除菜品数据
    void deleteById(Long id);

    // 根据id查询菜品和关联的口味数据
    DishVO getByIdWithFlavor(Long id);

    // 根据主键修改菜品信息
    @AutoFill(type = AutoFillConstant.UPDATE)
    void update(Dish dish);
}
