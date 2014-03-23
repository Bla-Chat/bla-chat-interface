package net.michaelfuerst.xjcp.web;

import org.junit.Test;

import static org.junit.Assert.*;

public class HTTPTest {
	private static final String UNENCODED = "abcdefghijklmopqrstuvwxyz0123456789!\"§$%&/()=";
	private static final String ENCODED = "abcdefghijklmopqrstuvwxyz0123456789%21%22%C2%A7%24%25%26%2F%28%29%3D";
	
	@Test(expected = IllegalArgumentException.class)	
	public void invalidHTTPParam() {
		new HttpParameter("a00", "");
	}
	
	@Test
	public void encodingTest() {
		HttpParameter p = new HttpParameter("key", UNENCODED);
		
		assertEquals(p.getKey(), "key");
		assertEquals(p.getValue(), ENCODED);
	}
	
	@Test
	public void massEncode() {
		String[] s = new String[] {"Hello", " ", "World"};
		
		HttpParameter[] p = HttpParameter.fromStrings(s[0]);
		
		assertNotNull(p);
		assertEquals(p.length, 1);
		assertEquals(p[0].getKey(), s[0]);
		assertNull(p[0].getValue());
		
		p = HttpParameter.fromStrings(s[0], s[1]);
		
		assertNotNull(p);
		assertEquals(p.length, 1);
		assertEquals(p[0].getKey(), s[0]);
		assertEquals(p[0].getValue(), "+");
		
		p = HttpParameter.fromStrings(s);
		
		assertNotNull(p);
		assertEquals(p.length, 2);
		assertEquals(p[0].getKey(), s[0]);
		assertEquals(p[0].getValue(),"+");
		assertEquals(p[1].getKey(), s[2]);
		assertNull(p[1].getValue());
		
	}
}
