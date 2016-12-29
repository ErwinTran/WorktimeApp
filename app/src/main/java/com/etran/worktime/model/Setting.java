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
    private Time workTime;

    public Setting(Time begin, Time end, List<Time> endings, Time breakTime, Time lunchTime, Time workTime) {
        this.begin = begin;
        this.end = end;
        this.endings = endings;
        this.breakTime = breakTime;
        this.lunchTime = lunchTime;
        this.workTime = workTime;
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

    public void setEnd(Time end) {
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

    public Time getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Time workTime) {
        this.workTime = workTime;
    }

}
