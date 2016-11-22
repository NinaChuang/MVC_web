package com.acer.mvc.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acer.mvc.vo.UserVo;

public class UserDao extends BaseDao {
	
	/**
	 * Find User By name email
	 * @author Charlie
	 * @date 2016年11月21日上午11:22:28
	 * @return List<Map<String,String>>
	 * @param name
	 * @param email
	 * @return
	 */
	public List<Map<String, String>> findUser(String name, String email) {
		List<Map<String, String>> userMapList = new ArrayList<>();
		Connection connection = super.getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement("select * from H_USER where USER_NAME = ? AND USER_EMAIL = ?");
			
			prepareStatement.setString(1, name);
			prepareStatement.setString(2, email);
			
			ResultSet result = prepareStatement.executeQuery();
			while(result.next()) {
				Map<String, String> userMap = new HashMap<>();
				
				// Get user table data.
				String userName = result.getString("USER_NAME");
				String userAge = result.getString("USER_AGE");
				String userSex = result.getString("USER_SEX");
				String userAddress = result.getString("USER_ADDRESS");
				String userEmail = result.getString("USER_EMAIL");
				String userPhone = result.getString("USER_PHONE");
			
				// Prepare userMap
				userMap.put("userName", userName);
				userMap.put("userAge", userAge);
				userMap.put("userSex", userSex);
				userMap.put("userAddress", userAddress);
				userMap.put("userEmail", userEmail);
				userMap.put("userPhone", userPhone);
				
				userMapList.add(userMap);
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
		return userMapList;
	}
	
	/**
	 * Save User
	 * @author Charlie
	 * @date 2016年11月21日上午11:22:53
	 * @return int
	 * @param userVo
	 * @return
	 */
	public int saveUser(UserVo userVo) {
		Connection connection = super.getConnection();
		try {
			PreparedStatement prepareStatement = connection.
					prepareStatement("INSERT INTO DTS.H_USER("
							+ "USER_NAME,"
							+ "USER_AGE,"
							+ "USER_SEX,"
							+ "USER_ADDRESS,"
							+ "USER_EMAIL,"
							+ "USER_PHONE) VALUES (?,?,?,?,?,?)");
			
			prepareStatement.setString(1, userVo.getName());
			prepareStatement.setString(2, userVo.getAge());
			prepareStatement.setString(3, userVo.getSex());
			prepareStatement.setString(4, userVo.getCity()+ userVo.getTown());
			prepareStatement.setString(5, userVo.getEmail());
			prepareStatement.setString(6, userVo.getPhone());
			
			int count = prepareStatement.executeUpdate();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
