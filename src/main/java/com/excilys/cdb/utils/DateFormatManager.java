package com.excilys.cdb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatManager {
	
	public static String HTML_DATE_FORMAT="dd/MM/yyyy";
	
	public static String toDateString(LocalDateTime date, String dateFormat){
		if(date == null) return "";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		return date.format(formatter);
	}
	
	public static LocalDateTime parseDate(String dateStr, String dateFormat) throws DateFormatManagerException{
		
		Date date;
		try {
			date = new SimpleDateFormat(dateFormat).parse(dateStr);
			return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		} catch (ParseException e) {
			throw new DateFormatManagerException("Cannot parse: "+ dateStr+" with format: "+ dateFormat,e);
		}
		
	}
	
	public static boolean isValidStringFormat(String dateStr, String dateFormatStr){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(dateStr.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
	
	public static LocalDateTime parseHTMLDateString(String dateStr) throws DateFormatManagerException{
		return parseDate(dateStr, HTML_DATE_FORMAT);	
	}
	
	public static String toHTMLDateString(LocalDateTime date){
		return toDateString(date, HTML_DATE_FORMAT);	
	}
}
