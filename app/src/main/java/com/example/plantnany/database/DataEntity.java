package com.example.plantnany.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "user")
public class DataEntity {

    @PrimaryKey
    public int id;
    public String date;
    public int intakeWater;
    @Ignore
    public DataEntity() {

    }

    public DataEntity(int id, String date, int intakeWater) {

        this.id = id;
        this.date = date;
        this.intakeWater = intakeWater;
        

    }


    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getIntakeWater() {
        return intakeWater;
    }

    public void setIntakeWater(int intakeWater) {
        this.intakeWater = intakeWater;
    }


}
