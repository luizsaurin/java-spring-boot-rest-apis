package com.example.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeUtils {
	
	public static void main(String[] args) {
		
		String stringToEncode = "";

		System.out.println(new BCryptPasswordEncoder().encode(stringToEncode));
	}
}
