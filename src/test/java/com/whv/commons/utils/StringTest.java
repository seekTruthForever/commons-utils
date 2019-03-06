package com.whv.commons.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.whv.commons.utils.string.StringPad;

public class StringTest {

	@Test
	public void testLpad() {
		System.out.println(StringPad.lpad("12", "测试", 1, false));
		System.out.println(StringPad.lpad("12", "测试", 1, true));
		System.out.println(StringPad.lpad("12", '\0', 5));
	}
	@Test
	public void testRpad() {
		System.out.println(StringPad.rpad("12", "测试", 6, false));
		System.out.println(StringPad.rpad("12", "测试", 1, true));
	}
	@Test
	public void testBothpad() {
		System.out.println(StringPad.bothpad("12", '*', 100));
		System.out.println(StringPad.bothpad("12", "测试", 1));
		System.out.println(StringPad.bothpad("12", "测试", 1, true));
	}
}
