package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
import com.reggie.dto.SetmealPageQueryDTO;
import com.reggie.entity.Setmeal;
import com.reggie.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SetmealMapper {

    /**
     * 根据分类id查询套餐的数量
     * @param id
     * @return
     */
    Integer countByCategoryId(Long id);

    /**
     * 修改套餐状态
     * @param map
     */
    void updateStatusByIds(Map map);

    /**
     * 新增套餐
     * @param setmeal
     */
    @AutoFill(type = AutoFillConstant.INSERT)
    void insert(Setmeal setmeal);

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    Setmeal getById(Long id);

    /**
     * 根据id删除套餐
     * @param setmealId
     */
    void deleteById(Long setmealId);

    /**
     * 根据id查询套餐和套餐菜品关系
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * 根据id修改套餐
     *
     * @param setmeal
     */
    @AutoFill(type = AutoFillConstant.UPDATE)
    void update(Setmeal setmeal);

    /**
     * 根据id修改套餐状态
     * @param status
     * @param id
     */
    void updateStatusById(Integer status, Long id);

    /**
     * 动态条件查询套餐
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);
}
