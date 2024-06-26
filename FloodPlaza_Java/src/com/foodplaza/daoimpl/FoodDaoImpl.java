package com.foodplaza.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.dao.FoodDao;
import com.foodplaza.pojo.Food;
import com.foodplaza.utility.DBConnection;

public class FoodDaoImpl implements FoodDao
{
	String foodName,foodType,foodCategory;
	double foodPrice;
	int foodId;
	String query;
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	int row;
	Food f = new Food();
	List<Food> fl = new ArrayList<Food>();
	Scanner s=new Scanner (System.in);
	@Override
	public boolean addFood(Food f) 
	{
		
		query="insert into foodMenu(foodName,foodPrice,foodType,foodCategory)values(?,?,?,?)";
		try 
		{
			conn=DBConnection.establish();
			ps= conn.prepareStatement(query);
			ps.setString(1,f.getFoodName());
			ps.setDouble(2,f.getFoodPrice());
			ps.setString(3,f.getFoodType());
			ps.setString(4,f.getFoodCategory());
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
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		return false;
	}

	@Override
	public boolean updateFood(Food f) 
	{
		query="select FoodId from foodMenu";
		try 
		{
			conn=DBConnection.establish();
			ps=conn.prepareStatement(query);
			int nfoodid=f.getFoodId();
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int nid=rs.getInt("foodId");
				if(nid==nfoodid)
				{
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
					 
					query=" update foodMenu set FoodName=? ,Foodprice=? ,FoodType=? , FoodCategory=? where FoodId=?";
					ps= conn.prepareStatement(query);
					ps.setString(1,f.getFoodName());
					ps.setDouble(2,f.getFoodPrice());
					ps.setString(3,f.getFoodType());
					ps.setString(4,f.getFoodCategory());
					ps.setInt(5,nfoodid);
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
					System.out.println("FoodId doen't exits");
					break;
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
	public boolean deletFood(int foodId) 
	{
		query="select FoodId from foodMenu";
		try 
		{
			conn=DBConnection.establish();
			ps=conn.prepareStatement(query);
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int nid=rs.getInt("foodId");
				if(nid==foodId)
				{
					query="delete from foodMenu where FoodId=?";
					ps= conn.prepareStatement(query);
					ps.setInt(1,foodId);
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
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Food displayFood(String foodName) 
	{
		try
		{
			conn=DBConnection.establish();
			query="select * from foodMenu where FoodName=?";
			ps= conn.prepareStatement(query);
			ps.setString(1,foodName);
			ResultSet rs=ps.executeQuery();
			while (rs.next())
			{
				f.setFoodId(rs.getInt("foodId"));
				f.setFoodName(rs.getString("foodName"));
				f.setFoodType(rs.getString("foodType"));
				f.setFoodCategory(rs.getString("foodCategory"));
				f.setFoodPrice(rs.getDouble("foodprice"));
				//System.out.println(rs.getInt("foodId")+" "+rs.getString("foodName")+" "+rs.getString("foodType")+" "+rs.getString("foodCategory")+" "+rs.getDouble("foodprice")); 
			}
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public Food displayFood(int foodId)
	{

		try
		{
//			query="Select FoodId from foodMenu";
//			ps=conn.prepareStatement(query);
//			rs=ps.executeQuery();
//			while(rs.next())
//			{
//				f.setFoodId(rs.getInt("foodId"));
//				
//			}
//			
					
			conn=DBConnection.establish();
			query="select * from foodMenu where FoodId=?";
			ps= conn.prepareStatement(query);
			ps.setInt(1,foodId);
			rs=ps.executeQuery();
			while (rs.next())
			{
				int faa=rs.getInt("foodId");
				if(faa==foodId)
				{
					
					f.setFoodId(rs.getInt("foodId"));
					f.setFoodName(rs.getString("foodName"));
					f.setFoodType(rs.getString("foodType"));
					f.setFoodCategory(rs.getString("foodCategory"));
					f.setFoodPrice(rs.getDouble("foodprice"));
					
				}
				else
				{
					System.out.println("Id does'nt exits");
					
				}
				//System.out.println(rs.getInt("foodId")+" "+rs.getString("foodName")+" "+rs.getString("foodType")+" "+rs.getString("foodCategory")+" "+rs.getDouble("foodPrice")); 
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
	}

	@Override
	public List<Food> displayAllFood() 
	{
		query="Select * from foodMenu";
		try
		{
			conn=DBConnection.establish();
			ps= conn.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while (rs.next())
			{
				
					
				Food f = new Food();
				f.setFoodId(rs.getInt("foodId"));
				f.setFoodName(rs.getString("foodName"));
				f.setFoodType(rs.getString("foodType"));
				f.setFoodCategory(rs.getString("foodCategory"));
				f.setFoodPrice(rs.getDouble("foodprice"));
				
				fl.add(f);
			}
				
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return fl;
	}
	

}
