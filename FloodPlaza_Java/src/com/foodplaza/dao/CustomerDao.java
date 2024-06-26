package com.foodplaza.dao;

import java.util.List;

import com.foodplaza.pojo.Customer;

public interface CustomerDao 
{
	boolean addCustomer(Customer c);
	boolean updateCustommer(Customer c);
	boolean deleteCustomer(String email);
	Customer displayCustommer(int CustomerId);
	Customer displayCustommer(String CustomerName);
	List <Customer> displayAllCustomer();
}
