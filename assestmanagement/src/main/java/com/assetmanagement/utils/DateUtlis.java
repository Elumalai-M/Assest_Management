package com.assetmanagement.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.micrometer.common.util.StringUtils;
import lombok.experimental.Helper;


public class DateUtlis {

	private DateUtlis() {
		
		
	}
	
	public static LocalDate stringToLocalDate(String dateString)
	{
		if(StringUtils.isNotBlank(dateString)) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 LocalDate date = LocalDate.parse(dateString, formatter);
		  return date;	
		}
		return null;
	}
	
	public static String localDateToString(LocalDate date)
	{
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	 String dateString = date.format(formatter);
	 return dateString;	
	}
	
	
	
	

}
