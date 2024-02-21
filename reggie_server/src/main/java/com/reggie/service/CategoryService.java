package com.reggie.service;

import com.reggie.dto.CategoryDTO;
import com.reggie.dto.CategoryPageQueryDTO;
import com.reggie.entity.Category;
import com.reggie.result.PageResult;

import java.util.List;

public interface CategoryService {

    // 新增分类
    void save(CategoryDTO categoryDTO);

    // 分页查询
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    // 根据id删除分类
    void deleteById(Long id);

    /**
     * 修改分类
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 启用、禁用分类
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    List<Category> list(Integer type);

}
