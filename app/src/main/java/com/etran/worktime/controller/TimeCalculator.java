package com.etran.worktime.controller;

/**
 * Created by Erwin on 17.12.2016.
 */

public class TimeCalculator {

    private UserData userData;

    public TimeCalculator(UserData userData) {
        this.userData = userData;
    }

    public String calculateWorkedTime() {
        return "14:35";
    }

    public String calculateOverTime() {
        // could also be undertime
        return "4:55";
    }
}
