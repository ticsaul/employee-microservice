package com.microservices.employee.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
	private DateUtil() {
	}

	public static boolean isAdultByBirthdate(Date birthdate) {
		int yearBirthdate = convertToLocalDateViaInstant(birthdate).getYear();
		return LocalDate.now().getYear() - yearBirthdate >= Constants.AGE_ADULT;
	}

	private static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static boolean isDate1EqualsOrLowerThanDate2(Date date1, Date date2) {
		LocalDate localDate1 = convertToLocalDateViaInstant(date1);
		LocalDate localDate2 = convertToLocalDateViaInstant(date2);
		return !localDate1.isAfter(localDate2);
	}

	public static boolean isWorkedHoursAllow(int workedHours) {
		return workedHours <= Constants.MAX_WORKED_HOURS && workedHours >= 0;
	}
}
