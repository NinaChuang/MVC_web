package com.acer.mvc.controller;

//import java.util.regex.Pattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acer.mvc.model.Model;
import com.acer.mvc.view.GridLayoutFrame;
import com.acer.mvc.vo.UserVo;

public class Controller {

	private GridLayoutFrame gridLayoutFrame;
	private UserVo userVo;
//	private CityActionListener cityActionListener;
//	private SubmitActionListener submitActionListener;
	private Model model = new Model();

	public Controller(GridLayoutFrame gridLayoutFrame) {
		this.gridLayoutFrame = gridLayoutFrame;
		this.userVo = new UserVo();
//		this.cityActionListener = new CityActionListener(this);
//		this.submitActionListener = new SubmitActionListener(this);
	}

//	public CityActionListener getCityActionListener() {
//		return this.cityActionListener;
//	}
//
//	public SubmitActionListener getSubmitActionListener() {
//		return this.submitActionListener;
//	}

	public void refreshUserVo() {
		this.userVo.refresh(gridLayoutFrame);
	}

	public void refreshView() {
		this.gridLayoutFrame.refreshView(userVo);
	}
	
	public void refresherrormessage(Map<String ,String> msgMap) {
		
		 this.gridLayoutFrame.refresherrormessage(msgMap);
	}
	
	public List<Map<String,String>> getFilterTowns(String provinceNo, String cityNo) {
		 return getModel().getListMapTown(provinceNo, cityNo);
	}
	
	public List<Map<String, String>> getListMapCity() {
		
		return getModel().getListMapCity();
	}
	
    public Map<String , String> doSubmit(UserVo user){
    	
    	return this.doValidate(user);
    }
	
	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

	/**
	 * 
	 * public void refreshView(UserVo userVo) {
	 * this.jName.setText(userVo.getName()); this.jAge.setText(userVo.getAge());
	 * this.jEmail.setText(userVo.getEmail());
	 * this.jPhone.setText(userVo.getPhone()); if ("男".equals(userVo.getSex()))
	 * { this.jMan.setSelected(true); this.jFemale.setSelected(false); } else {
	 * this.jMan.setSelected(false); this.jFemale.setSelected(true); }
	 * this.jListCity.setSelectedItem(userVo.getCity());
	 * this.jListTown.setSelectedItem(userVo.getTown()); }
	 */
	private Map<String, String> doValidate(UserVo vo) {
		Map<String, String> map = new HashMap<String, String>();

		if (!EmailValidator.validate(vo.getEmail())) {
			map.put("email", "email輸入格式錯誤");
			System.out.println(">> vo.getEmail() = " + vo.getEmail());
		}

		if (!PhoneNumberValid.isPhoneNumberValid(vo.getPhone())) {
			map.put("phone", "手機號碼輸入格式錯誤");
		}
		return map;
	}

	public class SubmitActionListener implements ActionListener {

		private Controller controller;

		public SubmitActionListener(Controller controller) {
			super();
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// View -> VO
			controller.refreshUserVo();

			// Logic -> Validation
			Map<String, String> errorMap  = doValidate(controller.getUserVo());

			// VO -> View
			controller.refreshView();
			controller.refreshUserVo();
			controller.refresherrormessage(errorMap);
		}

		// refreshErrorMessage
		
		private Map<String, String> doValidate(UserVo vo) {
			Map<String, String> map = new HashMap<String, String>();

			if (!EmailValidator.validate(vo.getEmail())) {
				map.put("email", "email輸入格式錯誤");
				System.out.println(">> vo.getEmail() = " + vo.getEmail());
			}

			if (!PhoneNumberValid.isPhoneNumberValid(vo.getPhone())) {
				map.put("phone", "手機號碼輸入格式錯誤");
			}
			return map;
		}

	}



	public Model getModel() {
		return model;
	}

	public GridLayoutFrame getGridLayoutFrame() {
		return gridLayoutFrame;
	}
	
	

}



