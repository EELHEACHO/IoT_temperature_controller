package com.sleep.good.goodsleep_mediaproj;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eelhea on 2016-12-05.
 */

public class AlarmListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AlarmListItem> alarmListItems = new ArrayList<AlarmListItem>();

    public AlarmListAdapter(Context context, ArrayList<AlarmListItem> alarmListItems){
        this.context = context;
        this.alarmListItems = alarmListItems;
    }

    @Override
    public int getCount() {
        return alarmListItems.size();
    }

    @Override
    public AlarmListItem getItem(int position) {
        return alarmListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater group_inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = group_inflater.inflate(R.layout.alarm_list_item, parent, false);
        }

        ImageButton ib_alarm_delete = (ImageButton)convertView.findViewById(R.id.ib_alarm_delete);
        TextView tv_ampm = (TextView)convertView.findViewById(R.id.tv_ampm);
        TextView tv_alarm_time = (TextView)convertView.findViewById(R.id.tv_alarm_time);
        TextView tv_sun = (TextView)convertView.findViewById(R.id.tv_sun);
        TextView tv_mon = (TextView)convertView.findViewById(R.id.tv_mon);
        TextView tv_tue = (TextView)convertView.findViewById(R.id.tv_tue);
        TextView tv_wed = (TextView)convertView.findViewById(R.id.tv_wed);
        TextView tv_thurs = (TextView)convertView.findViewById(R.id.tv_thurs);
        TextView tv_fri = (TextView)convertView.findViewById(R.id.tv_fri);
        TextView tv_sat = (TextView)convertView.findViewById(R.id.tv_sat);
        Switch alarm_switch = (Switch)convertView.findViewById(R.id.alarm_switch);

        tv_ampm.setTextColor(Color.parseColor("#d7d7d7"));
        tv_alarm_time.setTextColor(Color.parseColor("#d7d7d7"));
        tv_sun.setTextColor(Color.parseColor("#d7d7d7"));
        tv_mon.setTextColor(Color.parseColor("#d7d7d7"));
        tv_tue.setTextColor(Color.parseColor("#d7d7d7"));
        tv_wed.setTextColor(Color.parseColor("#d7d7d7"));
        tv_thurs.setTextColor(Color.parseColor("#d7d7d7"));
        tv_fri.setTextColor(Color.parseColor("#d7d7d7"));
        tv_sat.setTextColor(Color.parseColor("#d7d7d7"));
        alarm_switch.setTextColor(Color.parseColor("#d7d7d7"));

        ib_alarm_delete.setImageDrawable(context.getResources().getDrawable(R.drawable.x));
        ib_alarm_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmListItems.remove(getItem(position));
            }
        });

        tv_ampm.setText(getItem(position).getAmpm());
        tv_alarm_time.setText(getItem(position).getTime());
        if(getItem(position).isSun()==true) { tv_sun.setTextColor(Color.parseColor("#f3acab")); } else {tv_sun.setTextColor(Color.parseColor("#d7d7d7"));}
        if(getItem(position).isMon()==true) { tv_mon.setTextColor(Color.parseColor("#f3acab")); } else {tv_mon.setTextColor(Color.parseColor("#d7d7d7"));}
        if(getItem(position).isTue()==true) { tv_tue.setTextColor(Color.parseColor("#f3acab")); } else {tv_tue.setTextColor(Color.parseColor("#d7d7d7"));}
        if(getItem(position).isWed()==true) { tv_wed.setTextColor(Color.parseColor("#f3acab")); } else {tv_wed.setTextColor(Color.parseColor("#d7d7d7"));}
        if(getItem(position).isThurs()==true) { tv_thurs.setTextColor(Color.parseColor("#f3acab")); } else {tv_thurs.setTextColor(Color.parseColor("#d7d7d7"));}
        if(getItem(position).isFri()==true) { tv_fri.setTextColor(Color.parseColor("#f3acab")); } else {tv_fri.setTextColor(Color.parseColor("#d7d7d7"));}
        if(getItem(position).isSat()==true) { tv_sat.setTextColor(Color.parseColor("#f3acab")); } else {tv_sat.setTextColor(Color.parseColor("#d7d7d7"));}
        alarm_switch.setChecked(true);

        return convertView;
    }
}
