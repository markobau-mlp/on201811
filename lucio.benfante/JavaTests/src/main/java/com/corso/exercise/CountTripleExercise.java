package com.corso.exercise;

public class CountTripleExercise {
	public int countTriple(String str) {
		int result = 0;
		int i = 0;
		while(i < str.length()) {
			int j = i+1;
			while(j < str.length() && str.charAt(i) == str.charAt(j)) {
				j++;
			}
			if (j-i > 2) {
				result += (j - i -2);
			}
			i = j;
		}
		return result;
	}
}
