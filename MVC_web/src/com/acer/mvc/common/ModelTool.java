package com.acer.mvc.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.acer.mvc.model.ConnectionFactory;
import com.acer.mvc.model.Model;

public class ModelTool {
	
	public final static String CITY_INSERT_SQL = "INSERT INTO DTS.H_CITY(PROVINCE_NO, CITY_NO, CITY_NAME) VALUES (?,?,?)";
	public final static String TOWN_INSERT_SQL = "INSERT INTO DTS.H_TOWN(PROVINCE_NO, CITY_NO, AREA_NO, AREA_NAME) VALUES (?,?,?,?)";
	
	public void insertTest() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(CITY_INSERT_SQL);
			prepareStatement.setString(1, "test");
			prepareStatement.setString(2, "test2");
			prepareStatement.setString(3, "test3");
			
			int count = prepareStatement.executeUpdate();
			System.out.println("count = " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Insert City by Model
	 * @author USER
	 * @date 2016年11月21日上午11:26:22
	 * @return void
	 */
	@Test
	public void insertCitys() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		
		Model model = new Model();
		List<Map<String, String>> listMapCity = model.getListMapCity();
		
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(CITY_INSERT_SQL);
			
			for(Map<String, String> cityMap : listMapCity) {
				String cityNo = cityMap.get("cityNo");
				String cityName = cityMap.get("cityName");
				String provinceNo = cityMap.get("provinceNo");
				
				prepareStatement.setString(1, cityNo);
				prepareStatement.setString(2, cityName);
				prepareStatement.setString(3, provinceNo);
				
				int count = prepareStatement.executeUpdate();
				if(count > 0) {
					System.out.println(cityMap.entrySet() + " insert success!");
				}
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
	
	/**
	 * Insert Town by Model
	 * @author USER
	 * @date 2016年11月21日上午11:26:39
	 * @return void
	 */
//	@Test
	public void insertTowns() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();
		
		Model model = new Model();
		List<Map<String, String>> listMapTown = model.getListMapTown();
		
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(TOWN_INSERT_SQL);
			
			for(Map<String, String> townMap : listMapTown) {
				String provinceNo = townMap.get("provinceNo");
				String cityNo = townMap.get("cityNo");
				String areaNo = townMap.get("areaNo");
				String areaName = townMap.get("areaName");
				
				prepareStatement.setString(1, provinceNo);
				prepareStatement.setString(2, cityNo);
				prepareStatement.setString(3, areaNo);
				prepareStatement.setString(4, areaName);
				
				int count = prepareStatement.executeUpdate();
				if(count > 0) {
					System.out.println(townMap.entrySet() + " insert success!");
				}
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
