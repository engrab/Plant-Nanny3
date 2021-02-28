package com.example.plantnany.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class AppUtils {


    public static int calculateIntake(int weight, int workTime) {
        return (int) ((((double) (weight * 100)) / 3.0d) + ((double) ((workTime) * 7)));
    }

    public static String getCurrentDate() {
        Calendar instance = Calendar.getInstance();
        return new SimpleDateFormat("dd-MM-yyyy").format(instance.getTime());
    }

    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^"
                    + "(?=.*[0-9])"                  //minimum one number
                    + "(?=.*[a-z])"                  //minimum one lower case character
                    + "(?=.*[A-Z])"                  //minimum one UPPER case character
                    + "(?=.*[a-zA-Z])"               //any character
                    + "(?=.*[@#$%^&+=])"             //minimum one special character
                    + "(?=\\S+$)"                    //no white spaces
                    + ".{6,}"                        //minimum length 6 characters
                    + "$");

    public static final Pattern PASSWORD_UPPERCASE_PATTERN =
            Pattern.compile("(?=.*[A-Z])" + ".{0,}");

    public static final Pattern PASSWORD_LOWERCASE_PATTERN =
            Pattern.compile("(?=.*[a-z])" + ".{0,}");

    public static final Pattern PASSWORD_NUMBER_PATTERN =
            Pattern.compile("(?=.*[0-9])" + ".{0,}");

    public static final Pattern PASSWORD_SPECIAL_CHARACTER_PATTERN =
            Pattern.compile("(?=.*[@#$%^&+=])" + ".{0,}");


}
