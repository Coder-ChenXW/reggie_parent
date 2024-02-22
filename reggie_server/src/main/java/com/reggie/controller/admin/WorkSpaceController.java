package com.reggie.controller.admin;

import com.reggie.result.R;
import com.reggie.service.WorkspaceService;
import com.reggie.vo.BusinessDataVO;
import com.reggie.vo.DishOverViewVO;
import com.reggie.vo.OrderOverViewVO;
import com.reggie.vo.SetmealOverViewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Author: ChenXW
 * @Date:2024/2/22 22:55
 * @Description: 工作台
 **/

@RestController
@RequestMapping("/admin/workspace")
@Slf4j
@Api(tags = "工作台相关接口")
public class WorkSpaceController {

    @Resource
    private WorkspaceService workspaceService;

    /**
     * @description: 工作台今日数据查询
     * @author: ChenXW
     * @date: 2024/2/22 22:57
     */
    @GetMapping("/businessData")
    @ApiOperation("工作台今日数据查询")
    public R<BusinessDataVO> businessData() {

        LocalDateTime begin = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);

        BusinessDataVO businessDataVO = workspaceService.getBusinessData(begin, end);

        return R.success(businessDataVO);
    }

    /**
     * 查询订单管理数据
     * @return
     */
    @GetMapping("/overviewOrders")
    @ApiOperation("查询订单管理数据")
    public R<OrderOverViewVO> orderOverView(){
        return R.success(workspaceService.getOrderOverView());
    }

    /**
     * 查询菜品总览
     * @return
     */
    @GetMapping("/overviewDishes")
    @ApiOperation("查询菜品总览")
    public R<DishOverViewVO> dishOverView(){
        return R.success(workspaceService.getDishOverView());
    }

    /**
     * 查询套餐总览
     * @return
     */
    @GetMapping("/overviewSetmeals")
    @ApiOperation("查询套餐总览")
    public R<SetmealOverViewVO> setmealOverView(){
        return R.success(workspaceService.getSetmealOverView());
    }
}

