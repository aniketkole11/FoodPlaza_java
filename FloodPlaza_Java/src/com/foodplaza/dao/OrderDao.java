package com.foodplaza.dao;

import java.util.List;


import com.foodplaza.pojo.Order;

public interface OrderDao 
{
	boolean placeOrder(Order c);
	List<Order>viewOrder(String email);
}
