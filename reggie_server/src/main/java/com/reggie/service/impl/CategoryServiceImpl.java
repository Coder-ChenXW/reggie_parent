package com.reggie.service.impl;

import com.reggie.constant.StatusConstant;
import com.reggie.dto.CategoryDTO;
import com.reggie.entity.Category;
import com.reggie.mapper.CategoryMapper;
import com.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: ChenXW
 * @Date:2024/2/20 21:35
 * @Description: 分类业务层
 **/

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public void save(CategoryDTO categoryDTO) {

        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        category.setStatus(StatusConstant.DISABLE);

        categoryMapper.insert(category);

    }
}
