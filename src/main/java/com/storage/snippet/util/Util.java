package com.storage.snippet.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    //private static final String TIME_ZONE = "GMT";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);


    public static String generateDate(int validity_in_seconds){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, validity_in_seconds);

        Date date = calendar.getTime();

        //sdf.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));

        return sdf.format(date);


    }

    public static Date convertStringToDate(String dateString) throws ParseException {

        return sdf.parse(dateString);


    }

    public static Date getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
}
