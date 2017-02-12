package com.etran.worktime.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.etran.worktime.R;
import com.etran.worktime.controller.EndListArrayAdapter;
import com.etran.worktime.controller.TimeCalculator;
import com.etran.worktime.controller.UserData;
import com.etran.worktime.model.Setting;
import com.etran.worktime.model.Time;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView time1;
    private TextView time2;
    private TextView overtime1;
    private TextView overtime2;
    private RelativeLayout timePanel;
    private ListView endLv;
    private ImageButton startBt;
    private ImageButton lunchBt;
    private ImageButton breakBt;
    private ImageButton endBt;

    private UserData userData;
    private TimeCalculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userData = UserData.getInstance();
        calculator = new TimeCalculator(userData);

        initializeComponents();
        initializeListeners();

        setTime();
        setOverTimeText();
    }

    private void initializeComponents() {
        time1 = (TextView) findViewById(R.id.time1);
        time2 = (TextView) findViewById(R.id.time2);
        overtime1 = (TextView) findViewById(R.id.overTime1);
        overtime2 = (TextView) findViewById(R.id.overTime2);
        timePanel = (RelativeLayout) findViewById(R.id.timePanel);
        endLv = (ListView) findViewById(R.id.endList);
        startBt = (ImageButton) findViewById(R.id.startButton);
        lunchBt = (ImageButton) findViewById(R.id.lunchButton);
        breakBt = (ImageButton) findViewById(R.id.breakButton);
        endBt = (ImageButton) findViewById(R.id.endButton);
    }

    private void initializeListeners() {
        timePanel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // acts as refresh
                finish();
                startActivity(getIntent());
            }
        });

        setEndListView();

        startBt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openStartTimeDialog(v);
            }
        });
        lunchBt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openLunchTimeDialog(v);
            }
        });
        breakBt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openBreakTimeDialog(v);
            }
        });
        endBt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                openEndTimeDialog(v);
            }
        });
    }

    private void setEndListView() {
        List<Time> endTimeList = userData.getCurrentSettings().getEndings();
        List<String> tempList = new ArrayList<>();

        // could be converted better
        // buuuuuuuut....
        // i was tired and lazy
        for(Time time : endTimeList) {
            tempList.add(time.toString());
        }

        String[] endTimes = new String[tempList.size()];
        endTimes = tempList.toArray(endTimes);

        endLv.setAdapter(new EndListArrayAdapter(this, endTimes));
    }

    // i was tired and lazy
    // leave me alone
    private void openStartTimeDialog(View v) {
        final Dialog dialog = new Dialog(v.getContext());
        dialog.setContentView(R.layout.dialog_time2);
        dialog.setTitle("Set Starttime");

        Setting setting = userData.getCurrentSettings();

        final TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.timePicker);

        timePicker.setHour(setting.getBegin().getHour());
        timePicker.setMinute(setting.getBegin().getMinute());
        timePicker.setIs24HourView(true);

        Button saveButton = (Button) dialog.findViewById(R.id.saveTimeBt);
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Setting setting = userData.getCurrentSettings();
                Time startTime = new Time(timePicker.getHour(), timePicker.getMinute());
                setting.setBegin(startTime);
                userData.setCurrentSettings(setting);
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
        dialog.setTitle("Set Lunchtime");

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
                Setting setting = userData.getCurrentSettings();
                Time lunchTime = new Time(0, numberPicker.getValue() * 5);
                setting.setLunchTime(lunchTime);
                userData.setCurrentSettings(setting);
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
        dialog.setTitle("Set Breaktime");

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
                Setting setting = userData.getCurrentSettings();
                Time breakTime = new Time(0, numberPicker.getValue() * 5);
                setting.setBreakTime(breakTime);
                userData.setCurrentSettings(setting);
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
        dialog.setTitle("Set Endtime");

        Setting setting = userData.getCurrentSettings();

        final TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.timePicker);

        timePicker.setHour(setting.getEnd().getHour());
        timePicker.setMinute(setting.getEnd().getMinute());
        timePicker.setIs24HourView(true);

        Button saveButton = (Button) dialog.findViewById(R.id.saveTimeBt);
        saveButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Setting setting = userData.getCurrentSettings();
                Time endTime = new Time(timePicker.getHour(), timePicker.getMinute());
                setting.getEndings().add(endTime);
                userData.setCurrentSettings(setting);
                dialog.dismiss();

                finish();
                startActivity(getIntent());
            }
        });
        dialog.show();
    }

    private void setTime() {
        // set worked time
        time1.setText(calculator.calculateWorkedTime());

        // set over/under-time
        time2.setText(calculator.calculateTimeToWork());
    }

    private void setOverTimeText() {
        if(calculator.getWorkedTime() > 8) {
            overtime1.setText(R.string.overtime_text_1);
            overtime2.setText(R.string.overtime_text_2);
        }
        // standard text is for undertime
    }
}
