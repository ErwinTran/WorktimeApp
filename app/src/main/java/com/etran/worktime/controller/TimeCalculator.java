package com.etran.worktime.controller;

import com.etran.worktime.model.Setting;
import com.etran.worktime.model.Time;

import java.util.Calendar;

/**
 * Created by Erwin on 17.12.2016.
 */

public class TimeCalculator {

    private UserData userData;

    public TimeCalculator(UserData userData) {
        this.userData = userData;
    }

    private Time getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        Time t = new Time(hour, minute);
        return new Time(hour, minute);
    }

    private Time getBreakTime() {
        Time breakTime = userData.getCurrentSettings().getBreakTime();
        if(breakTime.getDoubleTime() > 0.5) {
            return new Time(breakTime.getDoubleTime() - 0.5);
        }
        return new Time();
    }

    public String calculateWorkedTime() {
        double workedTime = calculatingWorkedTime();

        return new Time(workedTime).toString();
    }

    public String calculateTimeToWork() {
        double currentTime = getCurrentTime().getDoubleTime();
        double endTime = userData.getCurrentSettings().getEnd().getDoubleTime();

        // overtime
        if(calculatingWorkedTime() > 8) {
            double timeToWork = currentTime - endTime;
            return new Time(timeToWork).toString();
        }

        double timeToWork = endTime - currentTime;
        return new Time(timeToWork).toString();
    }

    private double calculatingWorkedTime() {
        // it's late and i'm lazy
        // don't judge me
        Setting userSettings = userData.getCurrentSettings();

        double currentTime = getCurrentTime().getDoubleTime();
        double beginning = userSettings.getBegin().getDoubleTime();
        double lunchTime = userSettings.getLunchTime().getDoubleTime();
        double breakTime = getBreakTime().getDoubleTime();

        double workedTime = currentTime - beginning - lunchTime - breakTime;
        return workedTime;
    }
}
