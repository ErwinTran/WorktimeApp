package com.etran.worktime.model;

/**
 * Created by Erwin on 17.12.2016.
 */

public class Time {
    private int hour;
    private int minute;

    public Time() {
        this(0, 0);
    }

    public Time(String time) {

    }

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public Time(double time) {
        this.hour = (int)time;
        this.minute = (int) (time * 60) % 60;
    }

    @Override
    public String toString() {
        String h = "" + hour;
        if(hour < 10) {
            h = "0" + hour;
        }

        String m = "" + minute;
        if(minute < 10) {
            m = "0" + minute;
        }

        return hour + ":" + minute;
    }

    public Double getDoubleTime() {
        // name might be misleading
        double time = hour;
        time = time + (minute / 60);
        return time;
    }
}
