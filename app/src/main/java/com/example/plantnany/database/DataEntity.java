package com.example.plantnany.database;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;



@Entity(tableName = "user")
public class DataEntity {




    @NonNull
    @PrimaryKey
    public String date;
    public int intakeWater;
    @Ignore
    public DataEntity() {

    }

    public DataEntity(@NonNull String  date, int intakeWater) {
        this.date = date;
        this.intakeWater = intakeWater;
    }

    public String   getDate() {
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
