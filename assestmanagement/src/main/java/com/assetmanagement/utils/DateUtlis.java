package com.assetmanagement.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.experimental.Helper;


public class DateUtlis {

	private DateUtlis() {
		
		
	}
	
	public static LocalDate stringToLocalDate(String dateString)
	{
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 LocalDate date = LocalDate.parse(dateString, formatter);
		  return date;	
	}
	
	public static String localDateToString(LocalDate date)
	{
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	 String dateString = date.format(formatter);
	 return dateString;	
	}
	
	
	
	

}
