package com.corso.activities.core.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalcTest {

	@Test
	public void testMillisecondsToHours() {
		float result = Calc.millisecondsToHours(10800000);
		assertEquals(3.0f, result, 0.00001);
	}

}
