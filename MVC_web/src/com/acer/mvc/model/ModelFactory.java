package com.acer.mvc.model;

import com.acer.mvc.constant.Config;

public class ModelFactory {
	
	private static ModelFactory modelFactory;
	
	private static IModel model;
	
	private ModelFactory() {
		
	}
	
	/**
	 * ModelFactory getInstance
	 * @author USER
	 * @date 2016年11月21日上午10:12:29
	 * @return ModelFactory
	 * @return
	 */
	public static ModelFactory getInstance() {
		System.out.println("ModelFactory getInstance()");
		if(modelFactory == null) {
			System.out.println("build new ModelFactory");
			modelFactory = new ModelFactory();
		}
		return modelFactory;
	}
	
	/**
	 * Get Model by class name
	 * @author USER
	 * @date 2016年11月21日上午11:24:12
	 * @return IModel
	 * @return
	 */
	public IModel getModel() {
		System.out.println("ModelFactory.getModel()");
		Class<?> act = null;
		try {
			act = Class.forName(Config.MODEL_CLASS);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			if(model == null) {
				model = (IModel) act.newInstance();
				System.out.println("build new Model");
				System.out.println("getModel = " + model.getClass().getName());
			}
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return model;
	}
}
