package com.example.plantnany.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "user")
public class DataModel {

    @PrimaryKey
    private Date time;

    private Date date;
    private int weight;
    private  int targetWater;
    private int intakeWater;
    private int seed;
    @Ignore
    public DataModel() {
    }

    public DataModel(Date time, Date date, int weight, int targetWater, int intakeWater, int seed) {
        this.time = time;
        this.date = date;
        this.weight = weight;
        this.targetWater = targetWater;
        this.intakeWater = intakeWater;
        this.seed = seed;
    }



    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getTargetWater() {
        return targetWater;
    }

    public void setTargetWater(int targetWater) {
        this.targetWater = targetWater;
    }

    public int getIntakeWater() {
        return intakeWater;
    }

    public void setIntakeWater(int intakeWater) {
        this.intakeWater = intakeWater;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }
}
