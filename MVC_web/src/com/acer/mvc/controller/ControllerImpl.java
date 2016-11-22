package com.acer.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acer.mvc.model.IModel;
import com.acer.mvc.model.Model;
import com.acer.mvc.model.ModelFactory;
import com.acer.mvc.vo.UserVo;

public class ControllerImpl implements IController {
	private IModel model;

	@Override
	public List<Map<String, String>> getFilterTowns(String provinceNo, String cityNo) {

		return getModel().getListMapTown(provinceNo, cityNo);
	}

	@Override
	public List<Map<String, String>> getListMapCity() {

		return getModel().getListMapCity();
	}

	@Override
	public Map<String, String> doSubmit(UserVo vo) {
		Map<String, String> validateMap = this.doValidate(vo);
		if(validateMap != null && validateMap.size() > 0) {
			return validateMap;
		}
		
		String name = vo.getName();
		String email = vo.getEmail();
		
		List<Map<String, String>> userMapList = getModel().findUser(name, email);
		if(userMapList != null && userMapList.size() > 0) {
			validateMap.put("userExist", "�ϥΪ̤w�s�b");
			return validateMap;
		}
		
		boolean isSave = getModel().saveUser(vo);
		if(!isSave) {
			System.out.println("I'm sentinel");
			validateMap.put("userSaveErr", "�s�ɥ���");
			return validateMap;
		}
		
		return validateMap;
	}

	private Map<String, String> doValidate(UserVo vo) {
		Map<String, String> map = new HashMap<String, String>();

		if (!EmailValidator.validate(vo.getEmail())) {
			map.put("email", "email��J�榡���~");
			System.out.println(">> vo.getEmail() = " + vo.getEmail());
		}

		if (!PhoneNumberValid.isPhoneNumberValid(vo.getPhone())) {
			map.put("phone", "������X��J�榡���~");
		}
		return map;
	}

	public IModel getModel() {
		ModelFactory modelFactory = ModelFactory.getInstance();
		model = modelFactory.getModel();
		return model;
	}
}
