package com.example.plantnany.utils;

public class AppUtils {


    public static double calculateIntake(int weight, int workTime) {
        return (((double) (weight * 100)) / 3.0d) + ((double) ((workTime / 6) * 7));
    }


}
