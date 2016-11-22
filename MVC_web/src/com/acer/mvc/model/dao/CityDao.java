package com.acer.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class CityDao extends BaseDao {
	
	/**
	 * Query all citys
	 * @author USER
	 * @date 2016年11月21日上午11:23:39
	 * @return List<Map<String,String>>
	 * @return
	 */
	public List<Map<String, String>> queryCity() {
		List<Map<String, String>> cityMapList = new ArrayList<>();
		Connection connection = super.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("select * from DTS.H_CITY");
			ResultSet result = prepareStatement.executeQuery();
			while(result.next()) {
				Map<String, String> cityMap = new HashMap<>();
				
				// Get city table data.
				String provinceNo = result.getString("PROVINCE_NO");
				String cityNo = result.getString("CITY_NO");
				String cityName = result.getString("CITY_NAME");
			
				// Prepare cityMap
				cityMap.put("provinceNo", provinceNo);
				cityMap.put("cityNo", cityNo);
				cityMap.put("cityName", cityName);
				
				cityMapList.add(cityMap);
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
		return cityMapList;
	}
}
