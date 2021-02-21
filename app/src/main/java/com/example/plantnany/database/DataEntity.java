package com.example.plantnany.database;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "user")
public class DataEntity {


    @NonNull
    @PrimaryKey
    public String date;
    public int intakeWater;


    @NonNull
    @ColumnInfo(name = "target_water")
    public int targetWater;

    @NonNull
    @ColumnInfo(name = "level")
    public int level;



    @NonNull
    @ColumnInfo(name = "plant_type")
    public int plantType;

    @NonNull
    @ColumnInfo(name =  "complete")
    public int isTargetCompleted;


    @Ignore
    public DataEntity() {

    }


    public DataEntity(@NonNull String date, int intakeWater, int targetWater, int level, int plantType, int isTargetCompleted) {
        this.date = date;
        this.intakeWater = intakeWater;
        this.targetWater = targetWater;
        this.level = level;
        this.plantType = plantType;
        this.isTargetCompleted = isTargetCompleted;

    }

    public String getDate() {
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


    public int getTargetWater() {
        return targetWater;
    }

    public void setTargetWater(int targetWater) {
        this.targetWater = targetWater;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPlantType() {
        return plantType;
    }

    public void setPlantType(int plantType) {
        this.plantType = plantType;
    }

    public int getIsTargetCompleted() {
        return isTargetCompleted;
    }

    public void setIsTargetCompleted(int isTargetCompleted) {
        this.isTargetCompleted = isTargetCompleted;
    }

}
