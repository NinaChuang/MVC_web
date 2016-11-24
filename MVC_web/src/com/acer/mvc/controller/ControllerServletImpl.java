package com.acer.mvc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.acer.mvc.vo.UserVo;

@WebServlet("/controllerServletImpl/")
public class ControllerServletImpl extends HttpServlet implements IController {

	@Override
	public List<Map<String, String>> getFilterTowns(String provinceNo, String cityNo) {

		return null;
	}

	@Override
	public List<Map<String, String>> getListMapCity() {

		return null;
	}

	@Override
	public Map<String, String> doSubmit(UserVo vo) {

		return null;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = "some text";
		
		String msg=request.getParameter("msg");
		System.out.println(text+","+msg);
		response.setContentType("text/plain"); // Set content type of the
												// response so that jQuery knows
												// what it can expect.
		response.setCharacterEncoding("UTF-8"); // You want world domination,
												// huh?
		response.getWriter().write(text+","+msg);
		
	}
}