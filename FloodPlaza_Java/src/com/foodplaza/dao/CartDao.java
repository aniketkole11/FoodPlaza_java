package com.foodplaza.dao;

import java.util.List;

import com.foodplaza.pojo.Cart;

public interface CartDao 
{
	boolean addCart(Cart ct);
	boolean deleteCart(int cartId);
	List<Cart>viewCart(String email);
}
