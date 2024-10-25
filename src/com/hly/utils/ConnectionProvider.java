package com.hly.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	
	public static Connection getConnection() {
		
		try {
			//resign a Driver of mysql
			Class.forName("com.mysql.cj.jdbc.Driver");
			//make connect with my localhost to driver that signed
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LoginAndSignUp","root","12345");
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	//main to test if can connect to the dtb
	public static void main(String[] args) {
		
		if(getConnection() == null) System.out.println("Loss");
		else System.out.println("Connected");
		
	}

}
