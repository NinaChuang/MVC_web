package com.acer.mvc.model.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import com.acer.mvc.model.ConnectionFactory;

public class BaseDao {

	private ConnectionFactory connectionFactory;
	
	public BaseDao() {
		connectionFactory = new ConnectionFactory();
	}
	public Connection getConnection() {
		return connectionFactory.getConnection();
	}
}
