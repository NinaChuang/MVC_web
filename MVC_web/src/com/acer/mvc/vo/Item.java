package com.acer.mvc.vo;

public class Item {
	
	private String value;
	private String value2;
	private String description;
	 public Item(String value,String value2 ,String description)
     {
         this.value = value;
         this.value2 = value2;
         this.description = description;
     }
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	@Override
	public String toString() {
		return description;
	}
}
