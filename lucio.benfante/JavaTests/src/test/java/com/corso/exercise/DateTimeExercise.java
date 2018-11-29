package com.corso.exercise;

import java.util.Calendar;
import java.util.Locale;

public class DateTimeExercise {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.MONTH));
		System.out.println(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ITALIAN));
	}

}
