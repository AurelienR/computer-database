package com.excilys.cdb.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Manage to parse, validate format string.
 *
 * @author AurelienR
 */
@Component
public class DateFormatManager {

  private static final String LOCAL_DATE_PROPERTY_KEY = "property.dateFormat";
  private static final String SERVLET_CONTEXT_PATH = "/spring/application-context.xml";

  private static final MessageSource messageSource;

  static {
    try (ClassPathXmlApplicationContext ctx =
        new ClassPathXmlApplicationContext(SERVLET_CONTEXT_PATH)) {
      messageSource = (MessageSource) ctx.getBean("messageSource", MessageSource.class);
      ctx.close();
    }
  }

  /**
   * Convert LocalDateTime to a string with the specified format.
   *
   * @param date date to convert to string
   * @param dateFormat dateformat use as string formatter
   * @return date string formated
   */
  public static String toDateString(LocalDateTime date, String dateFormat) {

    // Case date null
    if (date == null) {
      return null;
    }

    // Format
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
    return date.format(formatter);
  }

  /**
   * Parse a string date with a given date format to a LocalDateTime object.
   *
   * @param dateStr dateString to parse
   * @param dateFormat dateFormat to use for parsing
   * @return LocalDateTime related object
   * @throws DateFormatManagerException if date string is not parsable with the given date format
   */
  public static LocalDateTime parse(String dateStr, String dateFormat)
      throws DateFormatManagerException {

    // Null parameter case
    if (dateStr == null || dateStr.isEmpty()) {
      return null;
    }

    // Try parse
    Date date;
    try {
      date = new SimpleDateFormat(dateFormat).parse(dateStr);
      return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    } catch (ParseException e) {
      throw new DateFormatManagerException(
          "Cannot parse: " + dateStr + " with format: " + dateFormat, e);
    }

  }

  /**
   * Check whether if the passed string is matching the given date format.
   *
   * @param dateStr date String to check
   * @param dateFormatStr date format to compare with
   * @return true if date is matching format, else false
   */
  public static boolean isValidDateString(String dateStr, String dateFormatStr) {

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
   * Gets the local date format.
   *
   * @return the local date format
   */
  public static String getLocalDateFormat() {
    Locale local = LocaleContextHolder.getLocale();
    return messageSource.getMessage(LOCAL_DATE_PROPERTY_KEY, null, local);
  }

  /**
   * To local date string format.
   *
   * @param date the date to stringify
   * @return the string formatted date
   */
  public static String toLocalDateStringFormat(LocalDateTime date) {
    String dateFormat = getLocalDateFormat();
    return toDateString(date, dateFormat);
  }

  /**
   * Parses the local date string.
   *
   * @param dateStr the date string to parse
   * @return related LocalDateTime object
   */
  public static LocalDateTime parseLocal(String dateStr) {
    String dateFormat = getLocalDateFormat();
    return parse(dateStr, dateFormat);
  }

  /**
   * Checks if date string is valid with local date format.
   *
   * @param dateStr date string to parse
   * @return true, if is valid local date string
   */
  public static boolean isValidLocalDateString(String dateStr) {
    String dateFormat = getLocalDateFormat();
    return isValidDateString(dateStr, dateFormat);
  }

}
