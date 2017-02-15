package com.samarthgupta.userapphalanx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alamkanak.weekview.WeekView;

public class CalenderActivity extends AppCompatActivity {
    WeekView mWeekView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        mWeekView = (WeekView) findViewById(R.id.weekView);

    }
}
