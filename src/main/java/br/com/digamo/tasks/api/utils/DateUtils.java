package br.com.digamo.tasks.api.utils;

import java.time.LocalDate;

public class DateUtils {

	public static boolean isEqualOrFutureDate(LocalDate date) {
		return date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now());
	}

}
