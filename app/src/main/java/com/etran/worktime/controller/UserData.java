package com.etran.worktime.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.etran.worktime.model.Setting;
import com.etran.worktime.model.Time;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erwin on 17.12.2016.
 */

public class UserData {

    private static UserData instance;
    private Setting currentSettings;
    private Setting savedSetting;

    private Context context;

    public static final String PREFS_NAME = "ch.etran.worktime";
    private static final String SETTINGS_KEY = "MySettings";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private UserData(Context context) {
        // get settings

        if(sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        }
        editor = sharedPreferences.edit();

        // retrieve settings
        savedSetting = getSavedSettings();

        currentSettings = savedSetting;
    }

    public static UserData getInstance(Context context) {
        if(instance == null) {
            instance = new UserData(context);
        }
        return instance;
    }

    public Setting getSavedSetting() {
        return savedSetting;
    }

    public void setSavedSetting(Setting setting) {
        savedSetting = setting;
    }

    public Setting getCurrentSettings() {
        return currentSettings;
    }

    public void setCurrentSettings(Setting setting) {
        currentSettings = setting;
    }

    public void saveSettings() {
        Gson gson = new Gson();
        String json = gson.toJson(savedSetting);
        editor.putString(SETTINGS_KEY, json);
        editor.commit();

        currentSettings = savedSetting;
    }

    private Setting getSavedSettings() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(SETTINGS_KEY, "");
        if(json.isEmpty()) {
            return getDefaultSetting();
        }
        Setting setting = gson.fromJson(json, Setting.class);
        return setting;
    }

    private Setting getDefaultSetting() {

        List<Time> endings = new ArrayList<>();
        endings.add(new Time(16, 10));
        endings.add(new Time(16, 40));
        endings.add(new Time(17, 10));

        Setting setting = new Setting(new Time(7, 50),
                new Time(16, 40),
                endings,
                new Time(0, 30),
                new Time(0, 30),
                new Time(8, 0));
        return setting;
    }
}
