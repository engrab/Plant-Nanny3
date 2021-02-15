package com.example.plantnany.database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;


@Entity(tableName = "user")
public class DataEntity {



    @PrimaryKey(autoGenerate = true)
    public int id;



    public String date;
    public int intakeWater;
    @Ignore
    public DataEntity() {

        date = null;
    }

    public DataEntity(@NotNull String date, int intakeWater) {
        this.date = date;
        this.intakeWater = intakeWater;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
