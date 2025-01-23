package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/csv_aggregator?user=root&password=12345");
		} catch (ClassNotFoundException | SQLException e) {
			throw new SQLException("Database connection error", e);
		}
	}
}
