package com.corso.exercise;

public class ZipZapExercise {
	private int count = 0;
	
	public String zipZap(String str) {
		count++;
		if (count % 5 == 0) {
			return str;
		} else {
			StringBuilder result = new StringBuilder();
			int i = 0;
			while (i < str.length()) {
				if (i < str.length() - 2 && str.charAt(i) == 'z' && str.charAt(i + 2) == 'p') {
					result.append("zp");
					i += 3;
				} else {
					result.append(str.charAt(i));
					i++;
				}
			}
			return result.toString();
		}
	}
}
