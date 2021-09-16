package com.bookapp.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookDAO {
	static Connection connection;

	public static Connection openConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;

	}

	public static void closeConnection() {

		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
