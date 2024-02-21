package com.reggie.controller.admin;

import com.reggie.dto.DishDTO;
import com.reggie.dto.DishPageQueryDTO;
import com.reggie.entity.Dish;
import com.reggie.result.PageResult;
import com.reggie.result.R;
import com.reggie.service.DishService;
import com.reggie.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ChenXW
 * @Date:2024/2/21 10:25
 * @Description: 菜品管理
 **/

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品相关的接口")
@Slf4j
public class DishController {


    @Resource
    private DishService dishService;

    /**
     * @description: 新增菜品
     * @author: ChenXW
     * @date: 2024/2/21 10:28
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public R<String> add(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品:{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return R.success();
    }

    /**
     * @description: 菜品分页
     * @author: ChenXW
     * @date: 2024/2/21 11:12
     */
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public R<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {

        log.info("菜品分页查询:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);

        return R.success(pageResult);
    }

    /**
     * @description: 批量删除
     * @author: ChenXW
     * @date: 2024/2/21 11:26
     */
    @DeleteMapping
    @ApiOperation("批量删除菜品")
    public R<String> delete(@RequestParam List<Long> ids) {

        log.info("菜品批量删除:{}", ids);
        dishService.deleteBatch(ids);

        return R.success();
    }

    /**
     * @description: 根据id查询菜品和关联的口味数据
     * @author: ChenXW
     * @date: 2024/2/21 12:41
     */
    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品和关联的口味数据")
    public R<DishVO> getById(@PathVariable Long id) {

        return R.success(dishService.getByIdWithFlavor(id));
    }

    /**
     * @description: 修改菜品
     * @author: ChenXW
     * @date: 2024/2/21 12:53
     */
    @PutMapping
    @ApiOperation("修改菜品")
    public R<String> update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品:{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return R.success();
    }
    /**
     * 菜品起售、停售
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("菜品起售、停售")
    public R<String> startOrStop(@PathVariable Integer status, Long id){
        dishService.startOrStop(status,id);
        return R.success();
    }

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public R<List<Dish>> list(Long categoryId){
        List<Dish> list = dishService.list(categoryId);
        return R.success(list);
    }
}
