package com.foodplaza.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.OrderDao;
import com.foodplaza.pojo.Cart;
import com.foodplaza.pojo.Order;
import com.foodplaza.utility.DBConnection;

public class OrderDaoImpl implements OrderDao
{
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	
	String query;
	int cartId,foodId;
	Order o=new Order();
	List<Order> c1 = new ArrayList<Order>();


	@Override
	public boolean placeOrder(Order c) 
	{
		
		try
		{
			conn=DBConnection.establish();
			String query9="SELECT f.foodId, f.foodName, f.foodPrice ,c.foodQuantity,c.email FROM foodMenu AS f INNER JOIN Cart AS c ON f.foodId = c.foodId WHERE c.cartId=?";
			ps= conn.prepareStatement(query9);
			ps.setInt(1,c.getCartId());
			ResultSet rs1=ps.executeQuery();
			while (rs1.next())
			{
				query="insert into orderTable(foodName,email,totalBill,orderDate,foodId) values(?,?,?,?,?)";
				ps= conn.prepareStatement(query);
				ps.setInt(1,c.getCartId());
			
					double totalPrice=rs1.getInt("foodQuantity")*rs1.getInt("foodPrice");
					
					ps.setString(1,rs1.getString("foodName"));
					ps.setString(2,rs1.getString("email"));
					ps.setDouble(3,totalPrice);
					ps.setString(4,c.getOrderDate());
					ps.setInt(5,rs1.getInt("foodId"));
					int row=ps.executeUpdate();
					if(row>0)
					{
						return true;
						//System.out.println("added successfully");
					}
					else
					{
						return false;
						//System.out.println("added Unsuccessfully");

					}
					
				
			}
			
			
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
		}
		return false;
	}


	@Override
	public List<Order> viewOrder(String email)
	{
		try
		{
			query="select * from orderTable where email=?";
			conn=DBConnection.establish();
			ps= conn.prepareStatement(query);
			ps.setString(1, email);
			rs=ps.executeQuery();
			while(rs.next())
			{
				Order o=new Order();
				o.setOrderId(rs.getInt("orderId"));
				o.setFoodName(rs.getString("foodName"));
				o.setEmail(rs.getString("email"));
				o.setOrderDate(rs.getString("orderDate"));
				o.setFoodId(rs.getInt("foodId"));
				c1.add(o);
			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return c1;
	
	}


}
