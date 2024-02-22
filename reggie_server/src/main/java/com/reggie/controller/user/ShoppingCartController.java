package com.reggie.controller.user;

import com.reggie.dto.ShoppingCartDTO;
import com.reggie.entity.ShoppingCart;
import com.reggie.result.R;
import com.reggie.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车
 */
@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "C端-购物车接口")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    /**
     * @description: 添加购物车
     * @author: ChenXW
     * @date: 2024/2/22 15:43
     */
    @PostMapping("/add")
    @ApiOperation("添加购物车")
    public R<String> add(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("添加购物车:{}", shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return R.success();
    }

    /**
     * @description: 查看购物车
     * @author: ChenXW
     * @date: 2024/2/22 16:01
     */
    @GetMapping("/list")
    @ApiOperation("查看购物车")
    public R<List<ShoppingCart>> list() {
        return R.success(shoppingCartService.showShoppingCart());
    }

    /**
     * @description: 清空购物车
     * @author: ChenXW
     * @date: 2024/2/22 16:09
     */
    @DeleteMapping("/clean")
    @ApiOperation("清空购物车商品")
    public R<String> clean() {
        shoppingCartService.cleanShoppingCart();
        return R.success();

    }

    /**
     * @description: 删除购物车的商品
     * @author: ChenXW
     * @date: 2024/2/22 16:19
     */
    @PostMapping("/sub")
    @ApiOperation("删除购物车中的商品")
    public R<String> sub(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        shoppingCartService.subShoppingCart(shoppingCartDTO);
        return R.success();
    }

}
