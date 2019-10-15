package com.akvasoft.construction.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class DateTimeConverter {

    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    private SimpleDateFormat converterFormat = new SimpleDateFormat("HH:mm");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public int differenceInTwoTimes(String in, String out) throws ParseException {
        Date timeIn = converterFormat.parse(in);
        Date timeOut = converterFormat.parse(out);
        long difference = timeOut.getTime() - timeIn.getTime();
        return (int) (difference %= HOUR);
    }

    public Date parseDate(String date) {
        Date date1 = null;
        try {
            date1 = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
