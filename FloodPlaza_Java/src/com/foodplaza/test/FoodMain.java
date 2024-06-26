package com.foodplaza.test;
import com.foodplaza.daoimpl.FoodDaoImpl;
import com.foodplaza.pojo.Food;
import com.foodplaza.utility.DBConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodMain 
{
	public static void main(String[] args) 
	{
		int choise,foodId;
		boolean falg;
		Food falg1;
		int i = 0;
		Food f=new Food();
		String foodName,foodType,foodCategory;
		double foodPrice;
		do
		{
			
		
		System.out.println("******FOOD PLAZA******");
		System.out.println("**********************");
		System.out.println("1.add food\n2.update food\n3.delet food\n4.display food by name\n5.display food by foodId\n6.display all food\n7.exit");
		System.out.println("**********************");
		System.out.println("Enter your choise");
		Scanner s=new Scanner (System.in);
		choise=s.nextInt();
		 FoodDaoImpl fdi=new FoodDaoImpl();

		switch(choise)
		{
		case 1:
			 System.out.println("Enter food name");
			 foodName=s.next();
			 System.out.println("Enter food price");
			 foodPrice=s.nextDouble();
			 System.out.println("Enter food Type");
			 foodType=s.next();
			 System.out.println("Enter food Category");
			 foodCategory=s.next();
			 f.setFoodName(foodName);
			 f.setFoodPrice(foodPrice);
			 f.setFoodType(foodType);
			 f.setFoodCategory(foodCategory);
			 
			
			falg=fdi.addFood(f);
			 if(falg==true) 
			 {
				 System.out.println("Food added");
				 
			 }
			 else
			 {
				 System.out.println("Food is not added");
			 }
			 
			 break;
			 
		case 2:
			
			System.out.println("Enter foodId which you want to update");
			foodId=s.nextInt();
			f.setFoodId(foodId);
	
			 falg=fdi.updateFood(f);
			 if(falg==true) 
			 {
				 System.out.println("Food Menu updated");
			 }
			 else
			 {
				 System.out.println("Food Menu is not updated");
			 }
			 break;
		case 3:
			System.out.println("Enter foodId which you want to delete");
			foodId=s.nextInt();
			f.setFoodId(foodId);
	
			 falg=fdi.deletFood(foodId);
			 if(falg==true) 
			 {
				 System.out.println("Food  deleted");
				
			 }
			 else
			 {
				 System.out.println("Food id not deleted");
			 }	
			 break;
		case 4:
			System.out.println("Enter food name");
			foodName=s.next();

			falg1=fdi.displayFood(foodName);
			 System.out.println(falg1.getFoodId() +" "+falg1.getFoodName() +" "+falg1.getFoodType()+" "+falg1.getFoodCategory()+" "+falg1.getFoodPrice());
			 
			 
			 if(falg1==null) 
			 {
				 System.out.println("something went wrong");
				
			 }
			 
			 break;
		case 5:
			System.out.println("Enter foodId");
			foodId=s.nextInt();

			 falg1=fdi.displayFood(foodId);
			 if(f==null)
			 {
				 System.out.println("Not value");
			 }
			 else
			 {
				 System.out.println(falg1.getFoodId() +" "+falg1.getFoodName() +" "+falg1.getFoodType()+" "+falg1.getFoodCategory()+" "+falg1.getFoodPrice());

			 }


		
			 
			
			 break;
			 
		case 6:
			System.out.println(" list :");
			List<Food> fl = new ArrayList<Food>();
			fl=fdi.displayAllFood();
			
			for(Food falg2 : fl)
			{
			 System.out.println(falg2.getFoodId() +" "+falg2.getFoodName() +" "+falg2.getFoodType()+" "+falg2.getFoodCategory()+" "+falg2.getFoodPrice());
			}
			break;
		case 7:
			System.out.println("Thnak you");
			break;
			
		}
		}while(choise<=6);
		
	}

}
