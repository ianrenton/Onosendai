package com.vaguehope.onosendai.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringHelperTest {

	@Test
	public void itFindsFirstLine () throws Exception {
		assertEquals("abc", StringHelper.firstLine("abc"));
		assertEquals("abc", StringHelper.firstLine("abc\n"));
		assertEquals("abc", StringHelper.firstLine("abc\ndef"));
	}

	@Test
	public void itTruncatesFront () throws Exception {
		assertEquals("abcdef", StringHelper.maxLengthEnd("abcdef", 6));
		assertEquals("...efg", StringHelper.maxLengthEnd("abcdefg", 6));
		assertEquals("...hijkl", StringHelper.maxLengthEnd("abcdefghijkl", 8));
	}

	@Test
	public void itReplacesFirst () throws Exception {
		assertEquals("foo bqwe bat", StringHelper.replaceOnce("foo bar bat", "ar", "qwe"));
		assertEquals("foo b bat", StringHelper.replaceOnce("foo bar bat", "ar", ""));
		assertEquals(" bar bat", StringHelper.replaceOnce("foo bar bat", "foo", ""));
		assertEquals("foo bar ", StringHelper.replaceOnce("foo bar bat", "bat", ""));
	}

}
