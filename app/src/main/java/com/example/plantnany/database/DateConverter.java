package com.example.plantnany.database;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {



    public static Long toTimeStamp(Date date){
        return date == null? null : date.getTime();
    }



    public static Date toDate(Long timeStamp){
        return timeStamp == null? null : new Date(timeStamp);
    }

    public static String dateToString(long time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(new Date(time));
    }
}
