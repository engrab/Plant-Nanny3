package com.example.plantnany.database;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DBUtils {

    public static Date getDate() {

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getTime() {
        return Calendar.getInstance().getTime();
    }


}
