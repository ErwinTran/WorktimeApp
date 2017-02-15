package com.etran.worktime.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.etran.worktime.R;
import com.etran.worktime.controller.UserData;
import com.etran.worktime.model.Setting;
import com.etran.worktime.model.Time;

public class SettingsActivity extends AppCompatActivity {

    private Button settingsStartBt;
    private Button settingsBreakBt;
    private Button settingsLunchBt;
    private Button settingsEndBt;
    private Button settingsWorkBt;
    private Button settingsOkBt;

    private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        userData = UserData.getInstance(this);

        initializeComponents();
        initializeListeners();
    }

    private void initializeComponents() {
        settingsStartBt = (Button) findViewById(R.id.settingsStartBt);
        settingsBreakBt = (Button) findViewById(R.id.settingsBreakBt);
        settingsLunchBt = (Button) findViewById(R.id.settingsLunchBt);
        settingsEndBt = (Button) findViewById(R.id.settingsEndBt);
        settingsWorkBt = (Button) findViewById(R.id.settingsWorkBt);
        settingsOkBt = (Button) findViewById(R.id.settingsOKBt);

        Setting setting = userData.getCurrentSettings();
        settingsStartBt.setText(setting.getBegin().toString());
        settingsBreakBt.setText(setting.getBreakTime().toString());
        settingsLunchBt.setText(setting.getLunchTime().toString());
        settingsEndBt.setText(setting.getEnd().toString());
        settingsWorkBt.setText(setting.getWorkTime().toString());
    }

    private void initializeListeners() {
        Setting setting = userData.getCurrentSettings();

        settingsStartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStartTimeDialog(v);
            }
        });
        settingsBreakBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBreakTimeDialog(v);
            }
        });
        settingsLunchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLunchTimeDialog(v);
            }
        });
        settingsEndBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEndTimeDialog(v);
            }
        });
        settingsWorkBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWorkTimeDialog(v);
            }
        });

        settingsOkBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userData.saveSettings();
                finish();
            }
        });
    }

    // was lazy again
    // HA! copy and pasted this
    private void openStartTimeDialog(View v) {
        final Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog_time2);
        dialog.setTitle("Set Usual Starttime");

        Setting setting = userData.getCurrentSettings();

        final TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.timePicker);

        timePicker.setHour(setting.getBegin().getHour());
        timePicker.setMinute(setting.getBegin().getMinute());
        timePicker.setIs24HourView(true);

        Button saveButton = (Button) dialog.findViewById(R.id.saveTimeBt);
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Setting setting = userData.getSavedSetting();
                Time startTime = new Time(timePicker.getHour(), timePicker.getMinute());
                setting.setBegin(startTime);
                userData.setSavedSetting(setting);
                dialog.dismiss();

                finish();
                startActivity(getIntent());
            }
        });
        dialog.show();
    }

    private void openLunchTimeDialog(View v) {
        final Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog_time);
        dialog.setTitle("Set Average Lunchtime");

        final NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.numberPicker);
        String[] numbers = new String[125/5];
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.toString(i * 5);
        }

        numberPicker.setDisplayedValues(numbers);
        numberPicker.setMaxValue(numbers.length-1);
        numberPicker.setMinValue(0);
        numberPicker.setValue(6);
        numberPicker.setWrapSelectorWheel(false);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int newValue = newVal * 5;
            }
        });
        Button saveButton = (Button) dialog.findViewById(R.id.saveTimeBt);
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Setting setting = userData.getSavedSetting();
                Time lunchTime = new Time(0, numberPicker.getValue() * 5);
                setting.setLunchTime(lunchTime);
                userData.setSavedSetting(setting);
                dialog.dismiss();

                finish();
                startActivity(getIntent());
            }
        });
        dialog.show();
    }

    private void openBreakTimeDialog(View v) {
        final Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog_time);
        dialog.setTitle("Set Available Breaktime");

        final NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.numberPicker);
        String[] numbers = new String[95/5];
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.toString(i * 5);
        }

        numberPicker.setDisplayedValues(numbers);
        numberPicker.setMaxValue(numbers.length-1);
        numberPicker.setMinValue(0);
        numberPicker.setValue(6);
        numberPicker.setWrapSelectorWheel(false);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int newValue = newVal * 5;
            }
        });
        Button saveButton = (Button) dialog.findViewById(R.id.saveTimeBt);
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Setting setting = userData.getSavedSetting();
                Time breakTime = new Time(0, numberPicker.getValue() * 5);
                setting.setBreakTime(breakTime);
                userData.setSavedSetting(setting);
                dialog.dismiss();

                finish();
                startActivity(getIntent());
            }
        });
        dialog.show();
    }

    private void openEndTimeDialog(View v) {
        final Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog_time2);
        dialog.setTitle("Set Standard Endtime");

        Setting setting = userData.getSavedSetting();

        final TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.timePicker);

        timePicker.setHour(setting.getEnd().getHour());
        timePicker.setMinute(setting.getEnd().getMinute());
        timePicker.setIs24HourView(true);

        Button saveButton = (Button) dialog.findViewById(R.id.saveTimeBt);
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Setting setting = userData.getCurrentSettings();
                Time endTime = new Time(timePicker.getHour(), timePicker.getMinute());
                setting.setEnd(endTime);
                userData.setSavedSetting(setting);
                dialog.dismiss();

                finish();
                startActivity(getIntent());
            }
        });
        dialog.show();
    }

    private void openWorkTimeDialog(View v) {
        final Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog_time2);
        dialog.setTitle("Set Worktime");

        Setting setting = userData.getSavedSetting();

        final TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.timePicker);

        timePicker.setHour(setting.getWorkTime().getHour());
        timePicker.setMinute(setting.getWorkTime().getMinute());
        timePicker.setIs24HourView(false);

        Button saveButton = (Button) dialog.findViewById(R.id.saveTimeBt);
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Setting setting = userData.getCurrentSettings();
                Time workTime = new Time(timePicker.getHour(), timePicker.getMinute());
                setting.setWorkTime(workTime);
                userData.setSavedSetting(setting);
                dialog.dismiss();

                finish();
                startActivity(getIntent());
            }
        });
        dialog.show();
    }

}
