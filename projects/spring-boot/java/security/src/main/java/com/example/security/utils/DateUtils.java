package com.example.security.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String formatDate(Date date) {

		if(date == null) {
			return null;
		}

		return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
	}
}
