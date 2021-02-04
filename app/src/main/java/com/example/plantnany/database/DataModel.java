package com.example.plantnany.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "user")
public class DataModel {

    @PrimaryKey
    private long date;
    private float weight;
    private  float targetWater;
    private int intakeWater;
    @Ignore
    public DataModel() {
    }

    public DataModel(long date, float weight, float targetWater, int intakeWater) {

        this.date = date;
        this.weight = weight;
        this.targetWater = targetWater;
        this.intakeWater = intakeWater;

    }


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getTargetWater() {
        return targetWater;
    }

    public void setTargetWater(float targetWater) {
        this.targetWater = targetWater;
    }

    public int getIntakeWater() {
        return intakeWater;
    }

    public void setIntakeWater(int intakeWater) {
        this.intakeWater = intakeWater;
    }


}
