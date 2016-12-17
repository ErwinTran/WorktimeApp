package com.etran.worktime.controller;

import com.etran.worktime.model.Setting;
import com.etran.worktime.model.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erwin on 17.12.2016.
 */

public class UserData {

    private static UserData instance;
    private Setting currentSettings;
    private Setting savedSetting;

    private UserData() {
        // get settings

        // mock
        List<Time> endings = new ArrayList<>();
        endings.add(new Time(16, 10));
        endings.add(new Time(16, 50));
        endings.add(new Time(17, 10));

        savedSetting = new Setting(new Time(7, 50), new Time(16, 50), endings, new Time(0, 30), new Time(0, 30));
        currentSettings = savedSetting;
    }

    public static UserData getInstance() {
        if(instance == null) {
            instance = new UserData();
        }
        return instance;
    }

}
