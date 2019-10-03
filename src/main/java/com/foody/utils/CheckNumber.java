package com.foody.utils;

public class CheckNumber {
	
	public static boolean checkNumber(String code) {
		char [] arrayCode = code.toCharArray();
		
		if(arrayCode[0] == '0') {
			return false;
		}
		int count = 0;
		for (int i = 0; i < arrayCode.length; i++) {
			if( arrayCode[i]<'0' || arrayCode[i] > '9') {
				count ++;
			}
			if(count > 0)
				break;
		}
		if(count > 0) {
			return false;
		}else {
			return true;
		}
	}
}
