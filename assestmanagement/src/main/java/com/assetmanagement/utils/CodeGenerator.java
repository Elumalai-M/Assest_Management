package com.assetmanagement.utils;

import java.time.LocalDate;

import org.apache.commons.lang3.RandomStringUtils;

public class CodeGenerator {

	private CodeGenerator() {
	
	}
	
	public static String  customCodeGenerator(String prefix) {
		LocalDate date = LocalDate.now();
		StringBuffer buffer = new StringBuffer(prefix);
		buffer.append(date.getYear()).append(date.getMonthValue());
		String suffix = RandomStringUtils.randomNumeric(3);
		buffer.append(suffix);
		return buffer.toString();
	}
	
	
	

}
