package com.corso.exercise;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class WordCountExerciseTest {
	private static WordCountExercise instance;

	@BeforeClass
	public static void setUp() {
		instance = new WordCountExercise();
	}
	
	@Test
	public void testWordCount01() {
		Map<String, Integer> result = instance.wordCount(
				new String[] {"a", "b", "a", "c", "b"});
		
		
		Map<String, Integer> expected = new HashMap<>();
		expected.put("a", 2);
		expected.put("b", 2);
		expected.put("c", 1);
		
		assertEquals(expected, result);
	}

	@Test
	public void testWordCount02() {
		Map<String, Integer> result = instance.wordCount(
				new String[] {"c", "b", "a"});
		Map<String, Integer> expected = new HashMap<>();
		expected.put("a", 1);
		expected.put("b", 1);
		expected.put("c", 1);
		assertEquals(expected, result);
	}

	@Test
	public void testWordCount03() {
		Map<String, Integer> result = instance.wordCount(
				new String[] {"c", "c", "c", "c"});
		Map<String, Integer> expected = new HashMap<>();
		expected.put("c", 4);
		assertEquals(expected, result);
	}
	
	@Test
	public void testWordCount04() {
		Map<String, Integer> result = instance.wordCount(
				new String[0]);
		Map<String, Integer> expected = new HashMap<>();
		assertEquals(expected, result);
	}

	@Test
	public void testWordCount05() {
		Map<String, Integer> result = instance.wordCount(
				new String[] {"this", "and", "this", ""});
		Map<String, Integer> expected = new HashMap<>();
		expected.put("this", 2);
		expected.put("and", 1);
		expected.put("", 1);
		assertEquals(expected, result);
	}

	@Test
	public void testWordCount06() {
		Map<String, Integer> result = instance.wordCount(
				new String[] {"x", "y", "x", "Y", "X"});
		Map<String, Integer> expected = new HashMap<>();
		expected.put("x", 2);
		expected.put("y", 1);
		expected.put("Y", 1);
		expected.put("X", 1);
		assertEquals(expected, result);
	}

	@Test
	public void testWordCount07() {
		Map<String, Integer> result = instance.wordCount(
				new String[] {"123", "0", "123", "1"});
		Map<String, Integer> expected = new HashMap<>();
		expected.put("123", 2);
		expected.put("0", 1);
		expected.put("1", 1);
		assertEquals(expected, result);
	}

	@Test
	public void testWordCount08() {
		Map<String, Integer> result = instance.wordCount(
				new String[] {"d", "a", "e", "d", "a", "d",
							"b", "b", "z", "a", "a", "b",
							"z", "x", "b", "f", "x", "two",
							"b", "one", "two"});
		Map<String, Integer> expected = new HashMap<>();
		expected.put("d", 3);
		expected.put("a", 4);
		expected.put("e", 1);
		expected.put("b", 5);
		expected.put("z", 2);
		expected.put("x", 2);
		expected.put("f", 1);
		expected.put("two", 2);
		expected.put("one", 1);
		assertEquals(expected, result);
	}	
	
	@Test
	public void testWordCount09() {
		Map<String, Integer> result = instance.wordCount(
				new String[] {"apple", "banana", "apple",
						"apple", "tea", "coffee", "banana"});
		Map<String, Integer> expected = new HashMap<>();
		expected.put("apple", 3);
		expected.put("banana", 2);
		expected.put("tea", 1);
		expected.put("coffee", 1);
		assertEquals(expected, result);
	}	
}
