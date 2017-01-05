package com.etran.worktime.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.etran.worktime.R;
import com.etran.worktime.model.Time;

/**
 * Created by Erwin on 05.01.2017.
 */

public class EndListArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final String[] values;
    private UserData userData;
    private TimeCalculator calculator;

    public EndListArrayAdapter(Context context, String[] values) {
        super(context, R.layout.row_end_time, values);
        this.context = context;
        this.values = values;
        this.userData = UserData.getInstance();
        calculator = new TimeCalculator(userData);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_end_time, parent, false);
        TextView endTime = (TextView) rowView.findViewById(R.id.endTime);
        TextView workedTime = (TextView) rowView.findViewById(R.id.workedTime);
        ProgressBar timeBar = (ProgressBar) rowView.findViewById(R.id.timeBar);

        endTime.setText(values[position]);
        Time end = userData.getCurrentSettings().getEndings().get(position);
        workedTime.setText(calculator.calculateWorkedTimeUntilTime(end));

        return rowView;
    }
}
