package com.reggie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.reggie.constant.MessageConstant;
import com.reggie.constant.StatusConstant;
import com.reggie.dto.DishDTO;
import com.reggie.dto.DishPageQueryDTO;
import com.reggie.entity.Dish;
import com.reggie.entity.DishFlavor;
import com.reggie.exception.DeletionNotAllowedException;
import com.reggie.mapper.DishFlavorMapper;
import com.reggie.mapper.DishMapper;
import com.reggie.mapper.SetmealDishMapper;
import com.reggie.mapper.SetmealMapper;
import com.reggie.result.PageResult;
import com.reggie.service.DishService;
import com.reggie.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ChenXW
 * @Date:2024/2/21 10:31
 * @Description:
 **/

@Service
public class DishServiceImpl implements DishService {

    @Resource
    private DishMapper dishMapper;

    @Resource
    private DishFlavorMapper dishFlavorMapper;

    @Resource
    private SetmealDishMapper setmealDishMapper;

    @Resource
    private SetmealMapper setmealMapper;

    @Transactional
    @Override
    public void saveWithFlavor(DishDTO dishDTO) {

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);

        Long dishId = dish.getId();


        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            dishFlavorMapper.insertBatch(flavors);
        }


    }

    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {

        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void deleteBatch(List<Long> ids) {

        ids.forEach(id -> {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        });

        List<Long> semtealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);

        if (semtealIds != null && semtealIds.size() > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        ids.forEach(id -> {
            dishMapper.deleteById(id);
            dishFlavorMapper.deleteByDishId(id);
        });

    }

    @Override
    public DishVO getByIdWithFlavor(Long id) {

        // Dish dish = dishMapper.getById(id);
        // List<DishFlavor> dishFlavorList = dishFlavorMapper.getByDishId(id);
        // DishVO dishVO = new DishVO();
        // BeanUtils.copyProperties(dish, dishVO);
        // dishVO.setFlavors(dishFlavorList);
        //
        // return dishVO;

        return dishMapper.getByIdWithFlavor(id);
    }

    @Override
    public void updateWithFlavor(DishDTO dishDTO) {

        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        dishMapper.update(dish);

        dishFlavorMapper.deleteByDishId(dish.getId());

        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishDTO.getId());
            });
            dishFlavorMapper.insertBatch(flavors);
        }

    }

    /**
     * 菜品起售停售
     *
     * @param status
     * @param id
     */
    @Transactional
    public void startOrStop(Integer status, Long id) {
        // update dish set status = ? where id = ?
        dishMapper.updateStatusById(status,id);

        if (status == StatusConstant.DISABLE) {
            // 如果是停售操作，还需要将包含当前菜品的套餐也停售
            List<Long> dishIds = new ArrayList<>();
            dishIds.add(id);
            // select setmeal_id from setmeal_dish where dish_id in (?,?,?)
            List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(dishIds);
            if (setmealIds != null && setmealIds.size() > 0) {
                Map mapSetmeal = new HashMap();
                mapSetmeal.put("status", StatusConstant.DISABLE);
                mapSetmeal.put("ids", setmealIds);
                setmealMapper.updateStatusByIds(mapSetmeal);
            }
        }
    }

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    public List<Dish> list(Long categoryId) {
        Dish dish = Dish.builder()
                .categoryId(categoryId)
                .status(StatusConstant.ENABLE)
                .build();
        return dishMapper.list(dish);
    }

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    public List<DishVO> listWithFlavor(Dish dish) {
        List<DishVO> list = dishMapper.listWithFlavor(dish);
        return list;
    }
}



