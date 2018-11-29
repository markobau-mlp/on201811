package com.corso.exercise;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FibonacciExerciseTest {
	
	private static FibonacciExercise instance;
	
	@Before
	public void setUp() {
		instance = new FibonacciExercise();
	}
	
	@Test
	public void testFibonacci00() {
		assertEquals(0, instance.fibonacci(0));
	}

	@Test
	public void testFibonacci01() {
		assertEquals(1, instance.fibonacci(1));
	}

	@Test
	public void testFibonacci02() {
		assertEquals(1, instance.fibonacci(2));
	}

	@Test
	public void testFibonacci03() {
		assertEquals(2, instance.fibonacci(3));
	}

	@Test
	public void testFibonacci08() {
		assertEquals(21, instance.fibonacci(8));
	}

	@Test
	public void testFibonacciIterative00() {
		assertEquals(0, instance.fibonacciIterative(0));
	}

	@Test
	public void testFibonacciIterative01() {
		assertEquals(1, instance.fibonacciIterative(1));
	}

	@Test
	public void testFibonacciIterative02() {
		assertEquals(1, instance.fibonacciIterative(2));
	}

	@Test
	public void testFibonacciIterative03() {
		assertEquals(2, instance.fibonacciIterative(3));
	}

	@Test
	public void testFibonacciIterative08() {
		assertEquals(21, instance.fibonacciIterative(8));
	}
	
	@Test
	public void testFibonacciWithCache() {
		instance.fibonacci(8);
		for(int i=2; i <= 8; i++) {
			assertNotEquals(0, instance.cache[i]);
			assertEquals(instance.fibonacciNoCache(i), instance.cache[i]);
		}
	}

	@Test
	public void testFibonacciIterativeWithCache() {
		instance.fibonacciIterative(8);
		assertNotEquals(0, instance.cache[8]);
		assertEquals(instance.fibonacciNoCache(8), instance.cache[8]);
	}
	
}
