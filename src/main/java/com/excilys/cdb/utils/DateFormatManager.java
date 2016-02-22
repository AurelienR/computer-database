package com.excilys.cdb.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Manage to parse, validate format string
 * @author AurelienR
 *
 */
public class DateFormatManager {
	
	public static String HTML_DATE_FORMAT="yyyy-MM-dd";
	public static String DISPLAY_DATE_FORMAT="dd-MM-yyyy";
	
	/**
	 * Convert LocalDateTime to a string with the specified format
	 * @param date date to convert to string
	 * @param dateFormat dateformat use as string formatter
	 * @return
	 */
	public static String toDateString(LocalDateTime date, String dateFormat){
		
		// Case date null
		if(date == null) return null;
		
		// Format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		return date.format(formatter);
	}
	
	
	/**
	 * Parse a string date with a given date format to a LocalDateTime object
	 * @param dateStr dateString to parse
	 * @param dateFormat dateFormat to use for parsing
	 * @return LocalDateTime related object
	 * @throws DateFormatManagerException if date string is not parsable with the given date format
	 */
	public static LocalDateTime parseDate(String dateStr, String dateFormat) throws DateFormatManagerException{
		
		// Null parameter case
		if(dateStr == null || dateStr.isEmpty()) return null;
		
		// Try parse
		Date date;
		try {
			date = new SimpleDateFormat(dateFormat).parse(dateStr);
			return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		} catch (ParseException e) {
			throw new DateFormatManagerException("Cannot parse: "+ dateStr+" with format: "+ dateFormat,e);
		}
		
	}
	
	/**
	 * Check whether if the passed string is matching the given date format
	 * @param dateStr date String to check
	 * @param dateFormatStr date format to compare with
	 * @return true if date is matching format, else false
	 */
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
	
	/**
	 * Check if passed date string is matching HTML spec format : yyyy-MM-dd
	 * @param dateStr date string to parse
	 * @return true if date is matching format, else false
	 */
	public static boolean isValidHTMLStringFormat(String dateStr){
		return isValidStringFormat(dateStr, HTML_DATE_FORMAT);
	}
	
	/**
	 * Parse date string with HTML spec format: yyyy-MM-dd
	 * @param dateStr date String to parse
	 * @return true if date is matching format, else false
	 * @throws DateFormatManagerException date string is not matching the format
	 */
	public static LocalDateTime parseHTMLDateString(String dateStr) throws DateFormatManagerException{
		return parseDate(dateStr, HTML_DATE_FORMAT);	
	}
	
	/**
	 * Convert LocalDateTime object to HTML format string: yyyy-MM-dd
	 * @param date LocalDateTime to convert
	 * @return related date string
	 */
	public static String toHTMLDateString(LocalDateTime date){
		return toDateString(date, HTML_DATE_FORMAT);	
	}
	
	/**
	 * Convert parse date string with conventional display format string: dd/MM/yyyy
	 * @param dateStr date string to parse
	 * @return LocalDateTime related object
	 * @throws DateFormatManagerException
	 */
	public static LocalDateTime parseDisplayDateString(String dateStr) throws DateFormatManagerException{
		return parseDate(dateStr, DISPLAY_DATE_FORMAT);	
	}
	
	/**
	 * Convert LocalDateTime object to conventional display format string : dd/MM/yyyy
	 * @param date
	 * @return
	 */
	public static String toDisplayDateString(LocalDateTime date){
		return toDateString(date, DISPLAY_DATE_FORMAT);	
	}
}
