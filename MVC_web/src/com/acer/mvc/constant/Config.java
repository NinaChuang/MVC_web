package com.acer.mvc.constant;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.acer.mvc.controller.ControllerFactory;

public class Config {
	public static final String CONTROLLER_CLASS;
	public static final String MODEL_CLASS;
	public static final String DRIVER;
	public static final String URL;
	public static final String ACCOUNT;
	public static final String PSSD;
	
	
	static {
		Properties properties = new Properties();
		String configFile = "mvc_setting.properties";
		try {
			properties.load(ControllerFactory.class.getResourceAsStream("/"  + configFile));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		CONTROLLER_CLASS = properties.getProperty("controller");
		MODEL_CLASS = properties.getProperty("model");
		DRIVER = properties.getProperty("driver");
		URL = properties.getProperty("url");
		ACCOUNT = properties.getProperty("account");
		PSSD = properties.getProperty("pssd");
	}
}
