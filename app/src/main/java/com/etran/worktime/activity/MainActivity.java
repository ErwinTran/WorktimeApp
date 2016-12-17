package com.etran.worktime.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.etran.worktime.R;
import com.etran.worktime.controller.TimeCalculator;
import com.etran.worktime.controller.UserData;

public class MainActivity extends AppCompatActivity {

    private TextView time1;
    private TextView time2;
    private RelativeLayout timePanel;

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
    }

    private void initializeComponents() {
        time1 = (TextView) findViewById(R.id.time1);
        time2 = (TextView) findViewById(R.id.time2);
        timePanel = (RelativeLayout) findViewById(R.id.timePanel);
    }

    private void initializeListeners() {
        timePanel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

            }
        });
    }

    private void setTime() {
        // set worked time
        time1.setText(calculator.calculateWorkedTime());

        // set over/under-time
        time2.setText(calculator.calculateOverTime());
    }

}
