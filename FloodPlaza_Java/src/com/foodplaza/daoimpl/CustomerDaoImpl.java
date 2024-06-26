package com.foodplaza.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.dao.CustomerDao;
import com.foodplaza.pojo.Customer;
import com.foodplaza.pojo.Food;
import com.foodplaza.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao

{
	String query,customerName,email,password,addresss;
	int CustomerId;
	Long mobile_no;
	Customer c = new Customer();
	List<Customer> c1 = new ArrayList<Customer>();

	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	int row;
	Scanner s=new Scanner(System.in);
	@Override
	public boolean addCustomer(Customer c) 
	{
		query="insert into customer(CustomerName,email,password,addresss,mobile_no)values(?,?,?,?,?)";
	try 
	{
		conn=DBConnection.establish();
		ps= conn.prepareStatement(query);
		ps.setString(1,c.getCustomerName());
		ps.setString(2,c.getEmail());
		ps.setString(3,c.getPassword());
		ps.setString(4,c.getAddresss());
		ps.setLong(5,c.getMobile_no());
		
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
	public boolean updateCustommer(Customer c) 
	{

		query="select email from customer";
		try 
		{
			conn=DBConnection.establish();
			ps=conn.prepareStatement(query);
			String ncId=c.getEmail();
			rs=ps.executeQuery();
			while(rs.next())
			{
				String nid=rs.getString("email");
				if(nid.equals(ncId))
				{
					 System.out.println("Enter your name");
					 customerName=s.next();
				
					 System.out.println("Enter Email");
					 email=s.next();
					 System.out.println("Enter password");
					 password=s.next();
					 System.out.println("Enter address");
					 addresss=s.next();
					 System.out.println("Enter mobile_no");
					 mobile_no=s.nextLong();
					 
					 c.setCustomerName(customerName);
					 c.setEmail(email);
					 c.setPassword(password);
					 c.setAddresss(addresss);
					 c.setMobile_no(mobile_no);
					 
					
					query=" update customer set customerName=? ,email=? ,password=? , addresss=?,mobile_no=? where email=?";
					ps= conn.prepareStatement(query);
					ps.setString(1,c.getCustomerName());
					ps.setString(2,c.getEmail());
					ps.setString(3,c.getPassword());
					ps.setString(4,c.getAddresss());
					ps.setLong(5,c.getMobile_no());

					ps.setString(6,ncId);
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
					System.out.println("Id doen't exits");
					
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
	public boolean deleteCustomer(String email) 
	{
		try
		{
			query="select email from customer";

			conn=DBConnection.establish();
			ps=conn.prepareStatement(query);
		
			rs=ps.executeQuery();
			while(rs.next())
			{
				String nid=rs.getString("email");
				if(nid.equals(email))
				{
					query="delete from customer where email=?";

					ps=conn.prepareStatement(query);
					ps.setString(1, email);
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
	public Customer displayCustommer(int CustomerId) 
	{
		try
		{
			conn=DBConnection.establish();
			query="select * from customer where customerId=?";
			ps=conn.prepareStatement(query);
			ps.setInt(1, CustomerId);
			rs=ps.executeQuery();
			while(rs.next()) 
			{

						c.setCustomerId(rs.getInt("CustomerId"));
						c.setCustomerName(rs.getString("customerName"));
						c.setEmail(rs.getString("email"));
						c.setPassword(rs.getString("password"));
						c.setAddresss(rs.getString("addresss"));
						c.setMobile_no(rs.getLong("mobile_no"));
					
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public Customer displayCustommer(String CustomerName) 
	{
		try
		{
			conn=DBConnection.establish();
			query="select * from customer where customerName=?";
			ps=conn.prepareStatement(query);
			ps.setString(1,CustomerName);
			rs=ps.executeQuery();
			while(rs.next()) 
			{

						c.setCustomerId(rs.getInt("CustomerId"));
						c.setCustomerName(rs.getString("customerName"));
						c.setEmail(rs.getString("email"));
						c.setPassword(rs.getString("password"));
						c.setAddresss(rs.getString("addresss"));
						c.setMobile_no(rs.getLong("mobile_no"));
						
			}
					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		return c;
	}

	@Override
	public List<Customer> displayAllCustomer()
	{
		query="Select * from customer";
		try
		{
			conn=DBConnection.establish();
			ps= conn.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while (rs.next())
			{
				
				c.setCustomerId(rs.getInt("CustomerId"));
				c.setCustomerName(rs.getString("customerName"));
				c.setEmail(rs.getString("email"));
				c.setPassword(rs.getString("password"));
				c.setAddresss(rs.getString("addresss"));
				c.setMobile_no(rs.getLong("mobile_no"));
				
				c1.add(c);
			}
				
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return c1;
	}
	

}
