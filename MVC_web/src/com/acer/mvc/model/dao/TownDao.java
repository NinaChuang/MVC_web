package com.acer.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TownDao extends BaseDao {

	/**
	 * Query town by provinceNo and cityNo 
	 * @author USER
	 * @date 2016年11月21日上午11:23:09
	 * @return List<Map<String,String>>
	 * @param _provinceNo
	 * @param _cityNo
	 * @return
	 */
	public List<Map<String, String>> queryTown(String _provinceNo, String _cityNo) {
		List<Map<String, String>> townMapList = new ArrayList<>();
		Connection connection = super.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("select * from H_TOWN WHERE CITY_NO = ? AND PROVINCE_NO = ?");
			
			prepareStatement.setString(1, _cityNo);
			prepareStatement.setString(2, _provinceNo);
			
			ResultSet result = prepareStatement.executeQuery();
			while(result.next()) {
				Map<String, String> townMap = new HashMap<>();
				
				// Get town table data.
				String provinceNo = result.getString("PROVINCE_NO");
				String cityNo = result.getString("CITY_NO");
				String areaNo = result.getString("AREA_NO");
				String areaName = result.getString("AREA_NAME");
			
				// Prepare townMap
				townMap.put("provinceNo", provinceNo);
				townMap.put("cityNo", cityNo);
				townMap.put("areaNo", areaNo);
				townMap.put("areaName", areaName);
				
				townMapList.add(townMap);
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
		return townMapList;
	}
}
