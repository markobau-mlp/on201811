package com.corso.exercise.inheritance;

public class B extends A {
	private int b;

	public B() {
		this(30);
	}
	
	public B(int b) {
		super(10);
		b = 60;
	}
	
	public void init() {
		b = 10;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "B [b=" + b + ", a=" + a + "]";
	}
	
}
