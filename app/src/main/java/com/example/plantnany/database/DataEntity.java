package com.example.plantnany.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "user")
public class DataEntity {

    @PrimaryKey
    public int id;
    public long date;
    public int weight;
    public   int targetWater;
    public int intakeWater;
    @Ignore
    public DataEntity() {
    }

    public DataEntity(int id, long date, int weight, int targetWater, int intakeWater) {

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


}
