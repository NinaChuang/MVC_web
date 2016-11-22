package com.acer.mvc.vo;

import com.acer.mvc.view.GridLayoutFrame;

public class UserVo {

	private String name;
	private String age;
	private String sex;
	private String email;
	private String phone;
	private String city;
	private String town;

	public void refresh(GridLayoutFrame gridLayoutFrame) {
		this.name = gridLayoutFrame.getjName().getText();
		this.age = gridLayoutFrame.getjAge().getText();
		this.sex = gridLayoutFrame.getjMan().isSelected() ? "¨k" : "¤k";
		this.email = gridLayoutFrame.getjEmail().getText();
		this.phone = gridLayoutFrame.getjPhone().getText();
		this.city = (String) gridLayoutFrame.getjListCity().getSelectedItem();
		this.town = (String) gridLayoutFrame.getjListTown().getSelectedItem();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	@Override
	public String toString() {
		return "UserVo [name=" + name + ", age=" + age + ", sex=" + sex + ", email=" + email + ", phone=" + phone
				+ ", city=" + city + ", town=" + town + "]";
	}
}
