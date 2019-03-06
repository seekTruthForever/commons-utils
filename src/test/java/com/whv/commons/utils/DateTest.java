package com.whv.commons.utils;

import org.junit.Test;

import com.whv.commons.utils.date.DateTool;
import com.whv.commons.utils.date.Lunar;

public class DateTest {

	@Test
	public void testLunar() {
		Lunar lunar = DateTool.getLunar("20170725");
		System.out.println(lunar.getDay());
		System.out.println(lunar.getMonth());
		System.out.println(lunar.getYear());
		System.out.println(lunar.getLunar(2017, 7, 25));
		System.out.println(lunar.cyclical(lunar.getYearCyl()));
		System.out.println(lunar.cYear(lunar.getYear()));
		System.out.println(lunar.cyclical(lunar.getMonCyl()));
		System.out.println(lunar.cDay(lunar.getDay()));
	}

}
