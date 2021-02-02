package com.example.plantnany.model;

public class TimeModel {

    private int hour;
    private int mint;
    private boolean isEnable;
    public TimeModel(){

    }

    public TimeModel(int hour, int mint, boolean isEnable) {
        this.hour = hour;
        this.mint = mint;
        this.isEnable = isEnable;
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

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
