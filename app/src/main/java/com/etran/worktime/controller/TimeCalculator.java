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
        Setting userSettings = userData.getCurrentSettings();

        double currentTime = getCurrentTime().getDoubleTime();
        double beginning = userSettings.getBegin().getDoubleTime();
        double lunchTime = userSettings.getLunchTime().getDoubleTime();
        double breakTime = getBreakTime().getDoubleTime();

        double workedTime = currentTime - beginning - lunchTime - breakTime;

        return new Time (workedTime).toString();
    }

    public String calculateOverTime() {
        // could also be undertime
        return "04:55";
    }
}
