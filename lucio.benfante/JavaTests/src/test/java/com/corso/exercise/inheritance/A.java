package com.corso.exercise.inheritance;

public class A {
	int a;
	
	public A(int c) {
		init();
		a += c;
	}

	void init() {
		a = 50;
	}

	@Override
	public String toString() {
		return "A [a=" + a + "]";
	}
}
