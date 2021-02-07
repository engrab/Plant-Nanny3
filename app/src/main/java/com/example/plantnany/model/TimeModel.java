package com.example.plantnany.model;

public class TimeModel {

    private int hour;
    private int mint;
    public TimeModel(){

    }

    public TimeModel(int hour, int mint) {
        this.hour = hour;
        this.mint = mint;

    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMint() {
        return mint;
    }

    public void setMint(int mint) {
        this.mint = mint;
    }

}
