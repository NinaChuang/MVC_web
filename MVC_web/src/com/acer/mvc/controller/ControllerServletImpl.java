package com.acer.mvc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acer.mvc.model.IModel;
import com.acer.mvc.model.ModelFactory;
import com.acer.mvc.vo.UserVo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/controllerServletImpl/")
public class ControllerServletImpl extends HttpServlet implements IController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IModel model;
	private Gson gson;

	@Override
	public List<Map<String, String>> getFilterTowns(String provinceNo,
			String cityNo) {

		return getModel().getListMapTown(provinceNo, cityNo);
	}

	@Override
	public List<Map<String, String>> getListMapCity() {

		return getModel().getListMapCity();
	}

	@Override
	public Map<String, String> doSubmit(UserVo vo) {

		return null;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)

	throws ServletException, IOException {
		
		//Ū��request���F��A�String(data)
		gson = new Gson();
		JsonObject data = new Gson().fromJson(request.getReader(),
				JsonObject.class);
		String getCity = null;
		String getTown = null;
		String submit = null;
		if (data.get("getCity") != null) {
			getCity = data.get("getCity").getAsString();
		} else if (data.get("getTown") != null) {
			getTown = data.get("getTown").getAsString();
		} else if (data.get("submit") != null) {
			submit = data.get("submit").getAsString();
		}

		if (getCity != null) {
			String gsonCity = gson.toJson(getListMapCity());
			response.setContentType("text/plain"); // �^�Ǥ@�Ӧr��,�榡��UTF-8
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gsonCity);
		} else if (getTown != null) {
			System.out.println("getTown=" + getTown);
			String[] a = getTown.split(",");
			String provinceNo = "";
			String cityNo = "";
			provinceNo = a[0];
			cityNo = a[1];

			List<Map<String, String>> listMap = getFilterTowns(provinceNo,
					cityNo);

			System.out.println("=" + listMap.get(0).entrySet());

			String gsonTown = gson.toJson(listMap);
			response.setContentType("text/plain"); // �^�Ǥ@�Ӧr��,�榡��UTF-8
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gsonTown);
		} else if (submit != null) {

			// ���o��檺���
			String name = data.get("name").getAsString();
			String age = data.get("age").getAsString();
			String sex = data.get("sex").getAsString();
			String addressCity = data.get("addressCity").getAsString();
			String addressTown = data.get("addressTown").getAsString();
			String email = data.get("email").getAsString();
			String phone = data.get("phone").getAsString();

			// ����email, phone
			// �����~���ܴN return
			Map<String, String> errorMap = validate(email, phone);

			if (errorMap != null && errorMap.size() > 0) {
				String gsonTown = gson.toJson(errorMap);
				response.setContentType("text/plain"); // �^�Ǥ@�Ӧr��,�榡��UTF-8
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(gsonTown);
				return;
			}
			Map<String, String> validateMap = new HashMap<String, String>();

			// �ϥΪ̤w�s�b return
			List<Map<String, String>> userMapList = getModel().findUser(name,
					email);
			if (userMapList != null && userMapList.size() > 0) {
				System.out.println("userMapList != null");
				// new Map
				// ����~�T��
				validateMap.put("userExist", "�ϥΪ̤w�s�b");

				String gsonTown = gson.toJson(validateMap);
				response.setContentType("text/plain"); // �^�Ǥ@�Ӧr��,�榡��UTF-8
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(gsonTown);
				return;
			}

			// ���ҳq�L�s���Ʈw

			UserVo userVo = new UserVo();
			userVo.setName(name);
			userVo.setAge(age);
			userVo.setSex(sex);
			userVo.setCity(addressCity);
			userVo.setTown(addressTown);
			userVo.setEmail(email);
			userVo.setPhone(phone);

			//�O�_����x�s���\
			boolean isSave = getModel().saveUser(userVo);
			if (!isSave) {
				System.out.println("I'm sentinel");
				validateMap.put("userSaveErr", "�s�ɥ���");

				String gsonTown = gson.toJson(errorMap);
				response.setContentType("text/plain"); // �^�Ǥ@�Ӧr��,�榡��UTF-8
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(gsonTown);
				return;
			} else {
				errorMap.put("saveSuccess", "�s�ɦ��\");
				String gsonTown = gson.toJson(errorMap);
				response.setContentType("text/plain"); // �^�Ǥ@�Ӧr��,�榡��UTF-8
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(gsonTown);
				return;
			}

		}

	}

	public IModel getModel() {
		ModelFactory modelFactory = ModelFactory.getInstance();
		model = modelFactory.getModel();
		return model;
	}

	public static void main(String[] args) {
		Gson gson = new Gson();
		List<Map<String, String>> arrayList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("00", "001");
		arrayList.add(map);
		System.out.println(gson.toJson(arrayList));
	}

	// doValidate Email,Phone����
	private Map<String, String> doValidate(UserVo vo) {
		Map<String, String> map = new HashMap<String, String>();

		if (!EmailValidator.validate(vo.getEmail())) {
			map.put("email", "�榡���~");
			System.out.println(">> vo.getEmail() = " + vo.getEmail());
		}

		if (!PhoneNumberValid.isPhoneNumberValid(vo.getPhone())) {
			map.put("phone", "�榡���~");
			System.out.println(">> vo.getPhone() = " + vo.getPhone());
		}
		return map;
	}

	// ���ҧ�����ƶǨ�Map<String, String>,�A�˨�UserVo
	public Map<String, String> validate(String email, String phone) {
		UserVo uservo = new UserVo();
		uservo.setEmail(email);
		uservo.setPhone(phone);

		// Logic -> Validation
		Map<String, String> errorMap = doValidate(uservo);
		return errorMap;

	}

}
