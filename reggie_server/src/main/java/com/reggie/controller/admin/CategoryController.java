package com.reggie.controller.admin;

import com.reggie.dto.CategoryDTO;
import com.reggie.result.R;
import com.reggie.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: ChenXW
 * @Date:2024/2/20 21:29
 * @Description: 分类管理
 **/

@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类相关接口")
@Slf4j
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * @description: 新增分类
     * @author: ChenXW
     * @date: 2024/2/20 21:34
     */
    @PostMapping
    @ApiOperation("新增分类")
    public R<String> add(@RequestBody CategoryDTO categoryDTO) {

        log.info("新增分类:{}", categoryDTO);

        categoryService.save(categoryDTO);

        return R.success();
    }

}
