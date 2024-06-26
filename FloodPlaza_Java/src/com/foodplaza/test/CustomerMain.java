package com.foodplaza.test;
import com.foodplaza.pojo.Customer;
import com.foodplaza.utility.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.CustomerDaoImpl;
import com.foodplaza.daoimpl.FoodDaoImpl;
import com.foodplaza.pojo.Customer;

public class CustomerMain
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException 
	{
		Connection conn;
		ResultSet rs;
		Statement st;
		int choise;
		String query,customerName,email,password,addresss;
		int CustomerId;
		Customer c = new Customer();
		boolean falg;
		Customer flag;
		CustomerDaoImpl cdi=new CustomerDaoImpl();
		Long mobile_no;
		do
		{
			
		
		System.out.println("*******Welcome******");
		System.out.println("");
		System.out.println("1.add Customer\n2.update Customer\n3.delet Customer\n4.display Customer by Customer Name\n5.display Customer by CustomerId \n6.display all Customer\n7.exit");
		System.out.println("Enter your choise");
		Scanner s=new Scanner (System.in);
		choise=s.nextInt();
		switch(choise)
		{
		case 1:
			System.out.println("");
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
			 
			
			 falg=cdi.addCustomer(c);
			 if(falg==true) 
			 {
				 System.out.println("Customer added");
				 
			 }
			 else
			 {
				 System.out.println("Customer is not added");
			 }
			 
			
			
			break;
		case 2:
			
			System.out.println("Enter your email");
			email=s.next();
			c.setEmail(email);
			falg=cdi.updateCustommer(c);
			 if(falg==true) 
			 {
				 System.out.println("Customer updated");
			 }
			 else
			 {
				 System.out.println("Customer is not updated");
			 }
			
			break;
		case 3:
			System.out.println("Enter email");
			email=s.next();
			falg=cdi.deleteCustomer(email);
			if(falg==true)
			{
				System.out.println("delete customer succeessfully");
			}
			else
			{
				System.out.println("Something went wrong");
			}
			
			break;
		case 4:
			System.out.println("Enter Customer Name");
			customerName=s.next();
			c.setCustomerName(customerName);
			
			conn=DBConnection.establish();
			st=conn.createStatement();
			rs=st.executeQuery("select customerName from customer");
			
			while(rs.next()) 
			{
				String ncid=rs.getString("customerName");
				if(ncid.equals(customerName))
				{
					flag=cdi.displayCustommer(customerName);
					System.out.println(flag.getCustomerId()+" "+flag.getCustomerName()+" "+flag.getEmail()+" "+flag.getPassword()+" "+flag.getAddresss()+" "+flag.getMobile_no());

				}
				else
				{
					 System.out.println("Not value return");
					 break;

				}
				
			}
			 break;
		case 5:
			System.out.println("Enter customer Id");
			CustomerId=s.nextInt();
			c.setCustomerId(CustomerId);
			conn=DBConnection.establish();
			st=conn.createStatement();
			rs=st.executeQuery("select customerId from customer");
			
			while(rs.next()) 
			{
				int ncid=rs.getInt("customerId");
				if(ncid==CustomerId)
				{
					flag=cdi.displayCustommer(CustomerId);
					System.out.println(flag.getCustomerId()+" "+flag.getCustomerName()+" "+flag.getEmail()+" "+flag.getPassword()+" "+flag.getAddresss()+" "+flag.getMobile_no());

				}
				else
				{
					 System.out.println("Not value return");
					 break;

				}
				
			}

			 break;
		case 6:
			List<Customer> f1=new ArrayList<Customer>();
			System.out.println("List");
			f1=cdi.displayAllCustomer();
			for(Customer falg3: f1)
			{
				System.out.println(falg3.getCustomerId()+" "+falg3.getCustomerName()+" "+falg3.getEmail()+" "+falg3.getPassword()+" "+ falg3.getAddresss()+" "+falg3.getMobile_no());
			}
			
			
			 break;
		case 7:
			System.out.println("Thank you");
			break;
			

			
		}
		
		}while(choise<7);	
	}

}
