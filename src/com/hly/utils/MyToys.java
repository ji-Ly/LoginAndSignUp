package com.hly.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyToys {
	
	public static boolean getEmail(String email) {
		
		
			try {
				String regrex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
				Pattern pattern = Pattern.compile(regrex);
				Matcher matcher = pattern.matcher(email);
				if(matcher.matches()) {
					return true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			
		}
			return false;
}
}