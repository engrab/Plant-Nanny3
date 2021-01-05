package com.example.plantnany.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public final class AppUtils {

    public static final String FIRST_RUN_KEY = "FIRST_RUN_KEY";

    public static final String NOTIFICATION_FREQUENCY_KEY = "NOTIFICATION_FREQUENCY_KEY";

    public static final String NOTIFICATION_MSG_KEY = "NOTIFICATION_MSG_KEY";

    public static final String NOTIFICATION_STATUS_KEY = "NOTIFICATION_STATUS_KEY";

    public static final String NOTIFICATION_TONE_URI_KEY = "NOTIFICATION_TONE_URI_KEY";

    public static final int PRIVATE_MODE = 0;

    public static final String SLEEPING_TIME_KEY = "SLEEPING_TIME_KEY";

    public static final String TOTAL_INTAKE = "TOTAL_INTAKE";

    public static final String USERS_SHARED_PREF = "USERS_SHARED_PREF";

    public static final String WAKEUP_TIME = "WAKEUP_TIME";

    public static final String WEIGHT_KEY = "WEIGHT_KEY";

    public static final String WORK_TIME_KEY = "WORK_TIME_KEY";


    public static double calculateIntake(int weight, int workTime) {
        return (((double) (weight * 100)) / 3.0d) + ((double) ((workTime / 6) * 7));
    }

    public static String getCurrentDate() {
        Calendar instance = Calendar.getInstance();
        return new SimpleDateFormat("dd-MM-yyyy").format(instance.getTime());
    }



}
