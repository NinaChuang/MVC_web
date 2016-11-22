package com.acer.mvc.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValid {
	public static boolean isPhoneNumberValid(String phoneNumber) {
		boolean isValid = false;

		// Initialize reg ex for phone number.
		String expression = "09[0-9]{2}[0-9]{3}[0-9]{3}";
		CharSequence inputStr = phoneNumber;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}
}
