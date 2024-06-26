package com.foodplaza.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.OrderDaoImpl;
import com.foodplaza.pojo.Cart;
import com.foodplaza.pojo.Order;
import com.foodplaza.utility.DBConnection;

public class OrderMain 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Order o=new Order();
		PreparedStatement ps;
		Connection conn;
		ResultSet rs;
		OrderDaoImpl odi =new OrderDaoImpl();
		boolean f;
		int cartId,foodId,choise;
		String email,orderDate;
		do
		{
			
		
		System.out.println("****Welcome*****");
		System.out.println("1.place Order  \n 2.view Order \n 3.exit");
		System.out.println("Entre your choise");
		Scanner s=new Scanner(System.in);
		
		choise=s.nextInt();
		switch(choise)
		{
		
		case 1:
			System.out.println("Enter CartId");
			cartId=s.nextInt();
			System.out.println("Entre date ");
			orderDate=s.next();
			o.setCartId(cartId);
			o.setOrderDate(orderDate);
			f=odi.placeOrder(o);
			if(f==true)
			{
				System.out.println("order Placed");
				
			}
			else
			{
				System.out.println("something went wrong");
			}
			break;
			
		case 2:
			
			System.out.println("Enter email ");
			email=s.next();
			o.setEmail(email);
			List<Order> c1 = new ArrayList<Order>();
			
			String query1="Select email from cart";
			conn=DBConnection.establish();
			ps= conn.prepareStatement(query1);
			rs=ps.executeQuery();	
			while(rs.next())
			{
				String sa=rs.getString("email");
				if(sa.equals(email))
				{
					System.out.println("List");
					c1=odi.viewOrder(email);
					for(Order falg3: c1)
					{

						System.out.println(falg3.getOrderId()+" "+falg3.getFoodName()+" "+falg3.getEmail()+" "+falg3.getOrderDate()+" "+falg3.getFoodId());
					}
					break;
				}
				else
				{
					System.out.println("Please register first");
					break;
				}
			}
			break;
			
		case 3:
			System.out.println("Thank youu");
			break;
			
		}
		}while(choise<3);	
	}

}
