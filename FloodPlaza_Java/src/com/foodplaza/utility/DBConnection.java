package com.foodplaza.utility;

import java.sql.*;

public class DBConnection 
{
	public static Connection establish() throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodplaza_java","root","12345");
		
		return conn;
	}
	
	
}
