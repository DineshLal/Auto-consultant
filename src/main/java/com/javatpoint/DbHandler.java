package com.javatpoint;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbHandler 
{
	static Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://10.211.50.35:3306/tamildi","tamildi","admin123");
			return con;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	
	}
}