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

		gson = new Gson();
		JsonObject data = new Gson().fromJson(request.getReader(),
				JsonObject.class);
		String getCity = null;
		String getTown = null;
		String validate = null;
		if (data.get("getCity") != null) {
			getCity = data.get("getCity").getAsString();
		} else if (data.get("getTown") != null) {
			getTown = data.get("getTown").getAsString();
		} else if(data.get("submit") != null){
			
			//取得表單的資料
			data.get("email");
			
			
			
			
			//驗證email, phone
			//有錯誤的話就 return
			
//			String gsonTown = gson.toJson(listMap);
//			response.setContentType("text/plain"); // 回傳一個字串,格式為UTF-8
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().write(gsonTown);
			
			
			
			//驗證通過存到資料庫
			
			
			
			
			
			
			
			
			
//			this.validate(email, phone);
		}

		if (getCity != null) {
			String gsonCity = gson.toJson(getListMapCity());
			response.setContentType("text/plain"); // 回傳一個字串,格式為UTF-8
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
			response.setContentType("text/plain"); // 回傳一個字串,格式為UTF-8
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gsonTown);
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

	public Map<String, String> validate(String email, String phone) {
		UserVo uservo = new UserVo();
		uservo.setEmail(email);
		uservo.setPhone(phone);

		// Logic -> Validation
		Map<String, String> errorMap = doValidate(uservo);
		return errorMap;

	}

}
