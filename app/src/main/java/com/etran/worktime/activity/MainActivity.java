package com.etran.worktime.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.etran.worktime.R;

public class MainActivity extends AppCompatActivity {

    private TextView time1;
    private TextView time2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        initializeListeners();
    }

    private void initializeComponents() {

    }

    private void initializeListeners() {

    }

}
