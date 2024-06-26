package com.foodplaza.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.CartDaoImpl;
import com.foodplaza.pojo.Cart;
import com.foodplaza.pojo.Customer;
import com.foodplaza.utility.DBConnection;

public class CartMain
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{
		PreparedStatement ps;
		Connection conn;
		ResultSet rs;
		String query;
		Cart ct=new Cart();
		CartDaoImpl ctdi=new CartDaoImpl();
		int choise,foodId,cartId,foodQuantity;
		String foodName,email;
		boolean flag;
		
		do 
		{
		
		System.out.println("*******welcome*******");
		System.out.println("1.add cart \n 2.view cart \n 3.delete cart \n 4.exits ");
		System.out.println("Entre your choise");
		Scanner s=new Scanner(System.in);
		
		choise=s.nextInt();
		switch(choise)
		{
		case 1:
			System.out.println("Enter food name");
			foodName=s.next();
			System.out.println("Enter food Quantity");
			foodQuantity=s.nextInt();
			System.out.println("Enter email");
			email=s.next();
			ct.setFoodName(foodName);
			ct.setFoodQuantity(foodQuantity);
			ct.setEmail(email);
			flag=ctdi.addCart(ct);
			if(flag==true)
			{
				System.out.println("added" );
			}
			else
			{
				System.out.println("Unadded");
			}
			
			break;
		case 2:
			System.out.println("Enter your email");
			email=s.next();
			List<Cart> f1=new ArrayList<Cart>();
			String query1="Select email from customer";
			conn=DBConnection.establish();
			ps= conn.prepareStatement(query1);
			rs=ps.executeQuery();	
			while(rs.next())
			{
				String sa=rs.getString("email");
				if(sa.equals(email))
				{
					System.out.println("List");
					f1=ctdi.viewCart(email);
					for(Cart falg3: f1)
					{
						System.out.println(falg3.getFoodId()+" "+falg3.getFoodName()+" "+falg3.getFoodPrice()+" "+falg3.getFoodQuantity());
					}
					
				}
				else
				{
					System.out.println("Please register first");
					
				}
			}
		
			
			 break;
		case 3:
			
			System.out.println("Enter cart id");
			cartId=s.nextInt();
			flag=ctdi.deleteCart(cartId);
			if(flag==true)
			{
				System.out.println("Delete successfully");
			}
			else
			{
				System.out.println("something went wrong");
			}
			break;
		case 4:
			System.out.println("Thank you ");
			break;
			
		}
		
		}while(choise<4);
	}

}
