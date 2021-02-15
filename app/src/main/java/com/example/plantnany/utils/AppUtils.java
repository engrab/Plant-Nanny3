package com.example.plantnany.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppUtils {


    public static double calculateIntake(int weight, int workTime) {
        return (((double) (weight * 100)) / 3.0d) + ((double) ((workTime / 6) * 7));
    }

    public static String getCurrentDate() {
        Calendar instance = Calendar.getInstance();
        return new SimpleDateFormat("dd-MM-yyyy").format(instance.getTime());
    }



}
