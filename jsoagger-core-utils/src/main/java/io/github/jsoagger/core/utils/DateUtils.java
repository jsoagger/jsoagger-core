/*-
 * ========================LICENSE_START=================================
 * JSoagger 
 * %%
 * Copyright (C) 2019 JSOAGGER
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */


package io.github.jsoagger.core.utils;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

	public final static String	   DEFAULT_DATE_FORMAT			  = "dd/MM/yyyy HH:mm:ss Z";
	public final static String	   DATE_FORMAT_SHORT			  = "dd/MM/yyyy";
	public final static String	   HMI_DEFAULT_DATE_FORMAT_STRING = "EEE, MMM d";
	public final static String	   HMI_SHORT_DATE_FORMAT_STRING	  = "dd-MM-yyyy";
	public final static String	   DATE_FORMAT					  = "dd/MM/yyyy HH:mm:ss Z";
	public static final String	   UTC_TIME_ZONE				  = "UTC";
	public static SimpleDateFormat dateFormat					  = new SimpleDateFormat(DateUtils.DATE_FORMAT);

	public final static String	   FILE_SAVE_FORMAT				  = "ddMMyyyy_HHmmss_SSS";

	static {
		DateUtils.dateFormat.setTimeZone(TimeZone.getTimeZone(DateUtils.UTC_TIME_ZONE));
	}


	/**
	 * Format a date to given format, in UTC timezone (GMT +0)
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String UTCDateString(Date date, String format) {
		if (date == null) {
			return "-";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}


	/**
	 * Format a date to given format according to default configured timezone of
	 * JVM or User PC.
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String dateString(Date date, String format) {
		if (date == null) {
			return "-";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(TimeZone.getDefault());
		return dateFormat.format(date);
	}


	/**
	 * Format a date to given format, in UTC timezone (GMT +0). Using format :
	 * dd/MM/yyyy HH:mm:ss Z
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String UTCDateString(Date date) {
		if (date == null) {
			return "-";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		return dateFormat.format(date);
	}


	/**
	 * Format a date to given format according to default configured timezone of
	 * JVM or User PC. Using format : dd/MM/yyyy HH:mm:ss Z
	 * 
	 * @param date
	 * @param format
	 * @return String
	 */
	public static String dateString(Date date) {
		if (date == null) {
			return "-";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getDefault());
		return dateFormat.format(date);
	}


	/**
	 * Get current date in default timezone UTC
	 * 
	 * 
	 * @return the current time in UTC zone
	 */
	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DateUtils.UTC_TIME_ZONE));
		return calendar.getTime();
	}


	/**
	 * Get the current date string UTC zone
	 * 
	 * 
	 * @return The string representation of the date
	 */
	public static String getCurrentDateString() {
		Date currentDate = DateUtils.getCurrentDate();
		return DateUtils.dateFormat.format(currentDate);
	}


	/**
	 * Get the given date in string format: UTC timezone
	 * 
	 * @param date
	 *            The date
	 * 
	 * @return The date String in UTC time zone
	 */
	public static String getDateString(Date date) {
		if (date != null)
		    return DateUtils.dateFormat.format(date);
		return "";
	}


	/**
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String getDateString(LocalDateTime dateTime) {
		Date date = asDate(dateTime);
		return getDateString(date);
	}


	/**
	 * Get the given date in string.<br>
	 * Short format is dd-MM-yyyy, and long format dd-MM-yyyy HH:mm
	 * 
	 * @param date
	 *            The date
	 * 
	 * @return The date String in UTC time zone
	 */
	public static String getHMIFormatDateString(Date date, boolean shortFormat) {
		DateFormat format_fr = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRENCH);
		String result = format_fr.format(date);
		// String result = "";
		// if (date != null) {
		// SimpleDateFormat simpleDateFormat;
		// if (shortFormat) {
		// simpleDateFormat =
		// new SimpleDateFormat(
		// "EEE, MMM d, ''yy");
		// }
		// else {
		// simpleDateFormat =
		// new SimpleDateFormat(
		// DateUtils.HMI_DEFAULT_DATE_FORMAT_STRING);
		// }
		// result = simpleDateFormat.format(date);
		// }
		return result;
	}


	/**
	 * Transform a given date from utc to another time zone.
	 * 
	 * @param date
	 *            The date
	 * @param timezone
	 *            The timezone to where transform the date
	 * 
	 * @return Date
	 */
	public static Date getDate(Date date, String timezone) {
		String dateString = DateUtils.getDateString(date);
		SimpleDateFormat tmpDateFormat = new SimpleDateFormat(DateUtils.DATE_FORMAT);
		tmpDateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
		Date resultDate = null;
		try {
			resultDate = tmpDateFormat.parse(dateString);
		}
		catch (ParseException exception) {
			exception.printStackTrace();
		}
		return resultDate;
	}


	/**
	 * Parse and transform a date String to {@link Date}. Input date format must
	 * be dd/MM/yyyy HH:mm:ss Z if in normal format and dd/MM/yyyy if short
	 * format
	 * 
	 * @param dateString
	 *            The date
	 * @param shortFormat
	 *            if true, is parsed from dd/MM/yyyy format
	 * @return Date or null
	 */
	public static Date readFromString(String dateString, boolean shortFormat) {
		SimpleDateFormat tmpDateFormat = new SimpleDateFormat(DateUtils.DATE_FORMAT);
		Date result = null;
		if (shortFormat) {
			tmpDateFormat = new SimpleDateFormat(DateUtils.DATE_FORMAT_SHORT);
		}
		try {
			result = tmpDateFormat.parse(dateString);
		}
		catch (Exception e) {
		}
		return result;
	}


	public static Date fromString_1(String dateString, String format) {
		SimpleDateFormat tmpDateFormat = new SimpleDateFormat(format);
		Date result = null;
		try {
			result = tmpDateFormat.parse(dateString);
		}
		catch (Exception e) {
		}

		return result;
	}


	public static LocalDate fromString_2(String dateString, String format) {
		return asLocalDate(fromString_1(dateString, format));
	}


	public static LocalDateTime fromString_3(String dateString, String format) {
		return asLocalDateTime(fromString_1(dateString, format));
	}


	public static Date readFromString(String dateString, String format) {
		SimpleDateFormat tmpDateFormat = new SimpleDateFormat(format);
		Date result = null;
		try {
			result = tmpDateFormat.parse(dateString);
		}
		catch (Exception e) {
		}

		return result;
	}


	public static Date asDate(LocalDate localDate) {
		if (localDate == null) {
			return Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		}

		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}


	public static Date asDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}


	public static LocalDate asLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}


	public static LocalDateTime asLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}
