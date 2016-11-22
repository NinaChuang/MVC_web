package com.acer.mvc.model;

import java.util.List;
import java.util.Map;

import com.acer.mvc.vo.UserVo;

public interface IModel {
	/**
	 * Get all city
	 * @author USER
	 * @date 2016年11月21日上午11:25:02
	 * @return List<Map<String,String>>
	 * @return
	 */
	public List<Map<String, String>> getListMapCity();
	
	/**
	 * Get town by provinceNo and cityNo
	 * @author USER
	 * @date 2016年11月21日上午11:25:17
	 * @return List<Map<String,String>>
	 * @param provinceNo
	 * @param cityNo
	 * @return
	 */
	public List<Map<String, String>> getListMapTown(String provinceNo, String cityNo);
	
	/**
	 * Find user by name, email
	 * @author USER
	 * @date 2016年11月21日上午11:25:37
	 * @return List<Map<String,String>>
	 * @param name
	 * @param email
	 * @return
	 */
	public List<Map<String, String>> findUser(String name, String email);
	
	/**
	 * Save user
	 * @author USER
	 * @date 2016年11月21日上午11:25:49
	 * @return boolean
	 * @param userVo
	 * @return
	 */
	public boolean saveUser(UserVo userVo);
}
