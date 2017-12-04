package com.sleep.good.goodsleep_mediaproj;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eelhea on 2016-12-05.
 */

public class AlarmActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton icBackIcon;

    TimePicker timePicker;

    ToggleButton button_sun;
    ToggleButton button_mon;
    ToggleButton button_tue;
    ToggleButton button_wed;
    ToggleButton button_thurs;
    ToggleButton button_fri;
    ToggleButton button_sat;

    ImageButton ib_alarm_delete;

    Button btn_recommend_alarm;
    Button btn_save_alarm;
    ArrayList<AlarmListItem> alarmListItems = new ArrayList<AlarmListItem>();

    AlarmListAdapter adapter;
    ListView alarm_list;

    private AlarmRecommendDialog alarmRecommendDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_activity);

        toolbar = (Toolbar)findViewById(R.id.user_info_toolbar);
        setSupportActionBar(toolbar);

        icBackIcon = (ImageButton)findViewById(R.id.icBackIcon);
        icBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmActivity.this, ProjectMainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rightin, R.anim.leftout);
                finish();
            }
        });

        timePicker = (TimePicker)findViewById(R.id.timePicker);

        button_sun = (ToggleButton)findViewById(R.id.button_sun);
        button_sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_sun.isChecked()){
                    button_sun.setBackgroundResource(R.drawable.pink_box);
                    button_sun.setBackgroundColor(Color.parseColor("#f3acab"));
                    button_sun.setTextColor(Color.WHITE);
                } else {
                    button_sun.setBackgroundResource(R.drawable.pink_box);
                    button_sun.setTextColor(Color.parseColor("#d7d7d7"));
                }
            }
        });
        button_mon = (ToggleButton)findViewById(R.id.button_mon);
        button_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_mon.isChecked()){
                    button_mon.setBackgroundResource(R.drawable.pink_box);
                    button_mon.setBackgroundColor(Color.parseColor("#f3acab"));
                    button_mon.setTextColor(Color.WHITE);
                } else {
                    button_mon.setBackgroundResource(R.drawable.pink_box);
                    button_mon.setTextColor(Color.parseColor("#d7d7d7"));
                }
            }
        });
        button_tue = (ToggleButton)findViewById(R.id.button_tue);
        button_tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_tue.isChecked()){
                    button_tue.setBackgroundResource(R.drawable.pink_box);
                    button_tue.setBackgroundColor(Color.parseColor("#f3acab"));
                    button_tue.setTextColor(Color.WHITE);
                } else {
                    button_tue.setBackgroundResource(R.drawable.pink_box);
                    button_tue.setTextColor(Color.parseColor("#d7d7d7"));
                }
            }
        });
        button_wed = (ToggleButton)findViewById(R.id.button_wed);
        button_wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_wed.isChecked()){
                    button_wed.setBackgroundResource(R.drawable.pink_box);
                    button_wed.setBackgroundColor(Color.parseColor("#f3acab"));
                    button_wed.setTextColor(Color.WHITE);
                } else {
                    button_wed.setBackgroundResource(R.drawable.pink_box);
                    button_wed.setTextColor(Color.parseColor("#d7d7d7"));
                }
            }
        });
        button_thurs = (ToggleButton)findViewById(R.id.button_thurs);
        button_thurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_thurs.isChecked()){
                    button_thurs.setBackgroundResource(R.drawable.pink_box);
                    button_thurs.setBackgroundColor(Color.parseColor("#f3acab"));
                    button_thurs.setTextColor(Color.WHITE);
                } else {
                    button_thurs.setBackgroundResource(R.drawable.pink_box);
                    button_thurs.setTextColor(Color.parseColor("#d7d7d7"));
                }
            }
        });
        button_fri = (ToggleButton)findViewById(R.id.button_fri);
        button_fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_fri.isChecked()){
                    button_fri.setBackgroundResource(R.drawable.pink_box);
                    button_fri.setBackgroundColor(Color.parseColor("#f3acab"));
                    button_fri.setTextColor(Color.WHITE);
                } else {
                    button_fri.setBackgroundResource(R.drawable.pink_box);
                    button_fri.setTextColor(Color.parseColor("#d7d7d7"));
                }
            }
        });
        button_sat = (ToggleButton)findViewById(R.id.button_sat);
        button_sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_sat.isChecked()){
                    button_sat.setBackgroundResource(R.drawable.pink_box);
                    button_sat.setBackgroundColor(Color.parseColor("#f3acab"));
                    button_sat.setTextColor(Color.WHITE);
                } else {
                    button_sat.setBackgroundResource(R.drawable.pink_box);
                    button_sat.setTextColor(Color.parseColor("#d7d7d7"));
                }
            }
        });

        ib_alarm_delete = (ImageButton)findViewById(R.id.ib_alarm_delete);


        btn_save_alarm = (Button)findViewById(R.id.btn_save_alarm);
        btn_save_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAlarm();
            }
        });

        btn_recommend_alarm = (Button)findViewById(R.id.btn_recommend_alarm);

        alarm_list = (ListView)findViewById(R.id.alarm_list);
    }

    public void saveAlarm(){
        boolean sun = false;
        boolean mon = false;
        boolean tue = false;
        boolean wed = false;
        boolean thurs = false;
        boolean fri = false;
        boolean sat = false;

        int hourOfday = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();

        String AMPM = "AM";
        if(hourOfday>11)
        {
            hourOfday = hourOfday-12;
            AMPM = "PM";
        }

        String time = String.valueOf(hourOfday) + ":" + String.valueOf(minute);

        if(button_sun.isChecked()){ sun = true; }
        if(button_mon.isChecked()){ mon = true; }
        if(button_tue.isChecked()){ tue = true; }
        if(button_wed.isChecked()){ wed = true; }
        if(button_thurs.isChecked()){ thurs = true; }
        if(button_fri.isChecked()){ fri = true; }
        if(button_sat.isChecked()){ sat = true; }

        AlarmListItem item = new AlarmListItem(ib_alarm_delete,AMPM,time,sun,mon,tue,wed,thurs,fri,sat,false);
        alarmListItems.add(item);

        adapter = new AlarmListAdapter(getApplicationContext(), alarmListItems);
        alarm_list.setAdapter(adapter);

        button_sun.setBackgroundResource(R.drawable.pink_box);
        button_sun.setTextColor(Color.parseColor("#d7d7d7"));
        button_mon.setBackgroundResource(R.drawable.pink_box);
        button_mon.setTextColor(Color.parseColor("#d7d7d7"));
        button_tue.setBackgroundResource(R.drawable.pink_box);
        button_tue.setTextColor(Color.parseColor("#d7d7d7"));
        button_wed.setBackgroundResource(R.drawable.pink_box);
        button_wed.setTextColor(Color.parseColor("#d7d7d7"));
        button_thurs.setBackgroundResource(R.drawable.pink_box);
        button_thurs.setTextColor(Color.parseColor("#d7d7d7"));
        button_fri.setBackgroundResource(R.drawable.pink_box);
        button_fri.setTextColor(Color.parseColor("#d7d7d7"));
        button_sat.setBackgroundResource(R.drawable.pink_box);
        button_sat.setTextColor(Color.parseColor("#d7d7d7"));
    }

}
