package com.corso.exercise;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ZipZapExerciseTest {

	private ZipZapExercise instance;
	
	@Before
	public void setUp() {
		instance = new ZipZapExercise();
	}

	@After
	public void tearDown() {
		// nothing in this case...just as an example.
	}
	
	@Test
	public void testZipZap01() {
		String param = "zipXzap";
		String expected = "zpXzp";
		String result = instance.zipZap(param);
		assertEquals(expected, result);
	}

	private void executeZipZap(String param, String expected) {
		String result = instance.zipZap(param);
		assertEquals(expected, result);
	}

	@Test
	public void testZipZap02() {
		executeZipZap("zopXzup", "zpXzp");
	}

	@Test
	public void testZipZap03() {
		executeZipZap("zopzop", "zpzp");
	}

	@Test
	public void testZipZap05() {
		executeZipZap("zibzap", "zibzp");
	}

	@Test
	public void testZipZap06() {
		executeZipZap("zip", "zp");
	}

	@Test
	public void testZipZap07() {
		executeZipZap("zi", "zi");
	}
	
	@Test
	public void testZipZap08() {
		executeZipZap("z", "z");
	}
	
	
	@Test
	public void testZipZap09() {
		executeZipZap("", "");
	}
	@Test
	public void testZipZap10() {
		executeZipZap("zzp", "zp");
	}
	@Test
	public void testZipZap11() {
		executeZipZap("abcppp", "abcppp");
	}
	@Test
	public void testZipZap12() {
		executeZipZap("azbcppp", "azbcppp");
	}
	@Test
	public void testZipZap04() {
		executeZipZap("azbcpzpp", "azbcpzp");
	}
}
