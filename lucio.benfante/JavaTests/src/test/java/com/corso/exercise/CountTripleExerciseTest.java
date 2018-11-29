package com.corso.exercise;

import static org.junit.Assert.*;

import org.junit.Test;

public class CountTripleExerciseTest {

	@Test
	public void testCountTripleWithASingleCharacter() {
		CountTripleExercise instance =
				new CountTripleExercise();
		int result = instance.countTriple("a");
		assertEquals("String with a single character", 0, result);
	}

	@Test
	public void testCountTriple01() {
		CountTripleExercise instance =
				new CountTripleExercise();
		int result = instance.countTriple("abcXXXabc");
		assertEquals(1, result);		
	}

	@Test
	public void testCountTriple02() {
		CountTripleExercise instance =
				new CountTripleExercise();
		int result = instance.countTriple("xxxabyyyycd");
		assertEquals(3, result);		
	}

	@Test
	public void testCountTripleWithAnEmptyString() {
		CountTripleExercise instance =
				new CountTripleExercise();
		int result = instance.countTriple("");
		assertEquals("String with an empty string", 0, result);
	}

}
