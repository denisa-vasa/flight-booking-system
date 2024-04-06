package com.gisdev.crmshm.util.general;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DisplayHelper {

    public static String formatDate(LocalDate localDate, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDate);
    }

    public static String formatDate(LocalDate localDate) {
        return formatDate(localDate, "dd-MM-yyyy");
    }

    public static String formatDateFromDateTime(LocalDateTime localDatetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return formatter.format(localDatetime);
    }

    public static String numberFormatForValues(Double value) {
        NumberFormat formatter = new DecimalFormat("#,##0.00");
        return formatter.format(value);
    }

    public static String doubleDisplayerWith3Decimals(double nr2Display) {
        DecimalFormat displayFormat = new DecimalFormat("#0.000");
        return displayFormat.format(nr2Display);
    }

    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatter.format(dateTime);
    }
}
