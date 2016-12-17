package com.etran.worktime.model;

import java.util.List;

/**
 * Created by Erwin on 17.12.2016.
 */

public class Setting {

    private Time begin;
    private Time end;
    private List<Time> endings;
    private Time breakTime;
    private Time lunchTime;

    public Setting(Time begin, Time end, List<Time> endings, Time breakTime, Time lunchTime) {
        this.begin = begin;
        this.end = end;
        this.endings = endings;
        this.breakTime = breakTime;
        this.lunchTime = lunchTime;
    }

    public Time getBegin() {
        return begin;
    }

    public void setBegin(Time begin) {
        this.begin = begin;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd() {
        this.end = end;
    }

    public List<Time> getEndings() {
        return endings;
    }

    public void setEndings(List<Time> endings) {
        this.endings = endings;
    }

    public Time getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(Time breakTime) {
        this.breakTime = breakTime;
    }

    public Time getLunchTime() {
        return lunchTime;
    }

    public void setLunchTime(Time lunchTime) {
        this.lunchTime = lunchTime;
    }

}
