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

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;

        if(hour > 24 || hour < 0) {
            this.hour = 0;
        }
        if(minute > 60 || minute < 0) {
            this.minute = 0;
        }
    }

    public Time(double time) {
        if(time < 24 || time > 0) {
            this.hour = (int) time;
            this.minute = (int) (time * 60) % 60;
        }
        else {
            this.hour = 0;
            this.minute = 0;
        }
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

        return h + ":" + m;
    }

    public Double getDoubleTime() {
        // name might be misleading
        double time = hour;
        double min = minute;
        time = time + (min / 60);
        return time;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

}
