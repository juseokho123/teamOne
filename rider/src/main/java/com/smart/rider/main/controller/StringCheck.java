package com.smart.rider.main.controller;

public class StringCheck {
	
	public static boolean isNumeric(String string) {
		try {
			Double.parseDouble(string);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
}
