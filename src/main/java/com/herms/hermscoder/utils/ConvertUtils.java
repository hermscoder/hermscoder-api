package com.herms.hermscoder.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertUtils {

    private ConvertUtils(){}

    public static LocalDate stringToLocalDate(String dateStr) {
        if(!Utils.isEmptyOrNull(dateStr)) {
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateStr, formatter);
        }

        return null;
    }

    public static String localDateToString(LocalDate date) {
        if(date != null) {
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return date.format(formatter);
        }
        return null;
    }

}
