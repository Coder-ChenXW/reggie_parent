package com.reggie.service;

import com.reggie.dto.ShoppingCartDTO;
import com.reggie.entity.ShoppingCart;
import java.util.List;

public interface ShoppingCartService {

    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> showShoppingCart();

    void cleanShoppingCart();

    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);

}
