package com.hly.utils;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Tables {
	
	public static void main(String[] args) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("create table appuser(appuser_pk int AUTO_INCREMENT primary key, name varchar(100), email varchar(200),password varchar(100))");
			
			JOptionPane.showMessageDialog(null, "Table created successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
