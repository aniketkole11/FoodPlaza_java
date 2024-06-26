package com.foodplaza.dao;

import java.util.List;

import com.foodplaza.pojo.Food;

public interface FoodDao 
{
	boolean addFood(Food f);
	boolean updateFood(Food f);
	boolean deletFood(int foodId);
	Food displayFood(String foodName);//Food=userdefined return type
	Food displayFood(int foodId);
	List <Food> displayAllFood();
}
