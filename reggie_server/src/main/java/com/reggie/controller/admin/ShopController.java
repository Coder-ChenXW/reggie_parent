package com.reggie.controller.admin;

import com.reggie.constant.StatusConstant;
import com.reggie.result.R;
import com.reggie.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ChenXW
 * @Date:2024/2/21 14:05
 * @Description: 店铺的状态设置
 **/

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺操作相关接口")
@Slf4j
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PutMapping("/{status}")
    @ApiOperation("设置店铺营业状态")
    public R<String> setStatus(@PathVariable Integer status) {

        shopService.setStatus(status);

        return R.success();
    }

    @GetMapping("/status")
    @ApiOperation("查询店铺营业状态")
    public R<Integer> getStatus() {

        try {
            Integer status = shopService.getStatus();
            return R.success(status);
        } catch (Exception ex) {
            ex.printStackTrace();
            return R.success(StatusConstant.ENABLE);
        }


    }

}
