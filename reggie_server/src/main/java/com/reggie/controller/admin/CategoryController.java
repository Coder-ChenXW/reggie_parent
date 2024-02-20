package com.reggie.controller.admin;

import com.reggie.dto.CategoryDTO;
import com.reggie.dto.CategoryPageQueryDTO;
import com.reggie.result.PageResult;
import com.reggie.result.R;
import com.reggie.service.CategoryService;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @description: 分类分页查询
     * @author: ChenXW
     * @date: 2024/2/20 22:42
     */
    @ApiOperation("分类分页查询")
    @GetMapping("/page")
    public R<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {

        log.info("分页查询:{}", categoryPageQueryDTO);

        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);

        return R.success(pageResult);
    }


    @DeleteMapping
    @ApiOperation("删除分类")
    public R<String> deleteById(Long id) {

        log.info("删除分类:{}", id);
        categoryService.deleteById(id);
        return R.success();
    }

}
