package com.acer.mvc.controller;

import com.acer.mvc.constant.Config;

/**
 * 
 * @author Hank L
 *
 */
public class ControllerFactory {
	private IController iController;

	public IController getInstance() {
		Class<?> act = null;
		try {
			act = Class.forName(Config.CONTROLLER_CLASS);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			if (iController == null) {
				iController = (IController) act.newInstance();
			} else {
				return iController;
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return iController;
	}
}
