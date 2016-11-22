package com.acer.mvc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.acer.mvc.controller.Controller;

public class CityActionListener implements ActionListener {

	private Controller controller;

	public CityActionListener(Controller controller) {
		super();
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// View -> VO
		controller.refreshUserVo();
		// Logic
//		String[] towns = Model.cityTowns.get(controller.getUserVo().getCity());
//		controller.getModel().getFilterTowns(provinceNo, cityNo);
//		controller.getGridLayoutFrame().getjListTown().removeAllItems();
		
		
		
		// VO -> View
		controller.refreshView();
//		controller.refreshJListTown(towns);
		// controller.onChnageCity();
	}

}