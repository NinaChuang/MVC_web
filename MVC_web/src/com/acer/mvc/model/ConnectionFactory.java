package com.acer.mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.acer.mvc.constant.Config;

public class ConnectionFactory {
	
	public ConnectionFactory() {
	}
	
	/**
	 * Get Connection
	 * @author USER
	 * @date 2016年11月21日上午11:26:03
	 * @return Connection
	 * @return
	 */
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(Config.DRIVER);
			connection = DriverManager.getConnection(Config.URL,Config.ACCOUNT,Config.PSSD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	@Test
	public void jdbcTest() {
		Connection connection = new ConnectionFactory().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("select * from ACCOUNT");
			ResultSet result = prepareStatement.executeQuery();
			while(result.next()) {
				System.out.println(result.getString("LOGIN_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
