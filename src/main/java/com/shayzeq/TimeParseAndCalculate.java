package com.shayzeq;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeParseAndCalculate {

    public static long timeCalculator(String requestDateTime, String responceDateTime){

        // Формат даты и времени в лог файле 2018-06-28 14:41:36,525
        Long result = Long.valueOf(0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,S", Locale.getDefault());

        Date requestTime = null;
        Date responceTime = null;
        try {
            requestTime = dateFormat.parse(requestDateTime);
            responceTime = dateFormat.parse(responceDateTime);
            result = responceTime.getTime() - requestTime.getTime();
        } catch (ParseException ex) {
            System.out.println("Не удалось распарсить время!");
        }

        return result;
    }

}
