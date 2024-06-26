package com.foodplaza.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.CartDao;
import com.foodplaza.pojo.Cart;
import com.foodplaza.pojo.Customer;
import com.foodplaza.utility.DBConnection;

public class CartDaoImpl implements CartDao
{

	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	String query;
	int cartId,foodId,foodQuantity,row;
	double foodPrice;
	String foodName,Email;
	List<Cart> c1 = new ArrayList<Cart>();
	Cart ct=new Cart();
	@Override
	public boolean addCart(Cart ct) 
	{
		try
		{
			query="select FoodId ,foodPrice from foodMenu  where FoodName=?";
			conn=DBConnection.establish();
			ps=conn.prepareStatement(query);
			ps.setString(1, ct.getFoodName());
			rs=ps.executeQuery();
			if(rs.next())
			{
				foodId=rs.getInt("FoodId");
				foodPrice=rs.getDouble("foodPrice");
				query="insert into cart(foodId,fooName,foodQuantity,email,foodPrice)values(?,?,?,?,?)";
				ps=conn.prepareStatement(query);
				ps.setInt(1, foodId);
				ps.setString(2, ct.getFoodName());
				ps.setInt(3, ct.getFoodQuantity());
				ps.setString(4, ct.getEmail());
				ps.setDouble(5, foodPrice);
				row=ps.executeUpdate();
				if(row>0)
				{
					return true;
				}
				else
				{
					return false;
				}
				
			}
			else
			{
				System.out.println("foodName and foodId does'nt match for:  "+ct.getFoodName());
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean deleteCart(int cartId)
	{
		try
		{
			query="delete from cart where cartId=? ";
			conn=DBConnection.establish();
			ps=conn.prepareStatement(query);
			ps.setInt(1,cartId);
			row=ps.executeUpdate();
			if(row>0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	
		return false;
	}
	public List<Cart> viewCart(String email) 
	{
		try
		{
			conn=DBConnection.establish();
			query="SELECT f.foodId, f.foodName, f.foodPrice ,c.foodQuantity FROM foodMenu AS f INNER JOIN Cart AS c ON f.foodId = c.foodId WHERE c.email=?";
			ps= conn.prepareStatement(query);
			ps.setString(1,email);
			rs=ps.executeQuery();
					while (rs.next())
					{
						Cart ca=new Cart();
						ca.setFoodName(rs.getString("foodName"));
						ca.setFoodQuantity(rs.getInt("foodQuantity"));
						ca.setFoodPrice(rs.getDouble("foodPrice"));
						c1.add(ca);	
					}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return c1;
	}
}
