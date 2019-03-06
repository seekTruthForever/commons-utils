package com.whv.commons.utils;


import org.junit.Test;

import com.whv.commons.utils.security.SHAUtil;

public class MD5Test {

	@Test
	public void testMd5() {
		System.out.println(SHAUtil.MD5("ILoveYou"));
		System.out.println(SHAUtil.KL(SHAUtil.SHA256("ILoveYou")));
		System.out.println(SHAUtil.SHA256("ILoveYou"));
	}
	@Test
	public void testYH() {
		int[] array = {2,3,4,4,3,5,6,6,5,23,2,'t'};
		int v = 0;
		for (int i = 0;i < array.length;i++) {
		System.out.println(array[i]+"^"+v+"=" + (v^array[i]));
		v ^= array[i];
		}
		System.out.println("只出现一次的数是:" + v);
	}

}
