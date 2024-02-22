package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
import com.reggie.dto.CategoryPageQueryDTO;
import com.reggie.dto.DishPageQueryDTO;
import com.reggie.entity.Category;
import com.reggie.entity.Dish;
import com.reggie.vo.DishItemVO;
import com.reggie.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    Integer countByCategoryId(Long categoryId);

    /**
     * 插入菜品数据
     * @param dish
     */
    @AutoFill(type = AutoFillConstant.INSERT)
    void insert(Dish dish);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据主键查询菜品数据
     * @param id
     * @return
     */
    Dish getById(Long id);

    /**
     * 根据主键删除菜品数据
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据id查询菜品和关联的口味数据
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 根据主键修改菜品信息
     * @param dish
     */
    @AutoFill(type = AutoFillConstant.UPDATE)
    void update(Dish dish);

    /**
     * 修改菜品状态
     * @param status
     * @param id
     */
    void updateStatusById(Integer status, Long id);

    /**
     * 动态条件查询菜品
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);

    /**
     * 根据套餐id查询菜品
     * @param setmealId
     * @return
     */
    List<Dish> getBySetmealId(Long setmealId);

    /**
     * 动态条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);

    /**
     * 根据套餐id查询菜品选项
     * @param setmealId
     * @return
     */
    List<DishItemVO> getDishItemBySetmealId(Long setmealId);

    Integer countByMap(Map map);
}
