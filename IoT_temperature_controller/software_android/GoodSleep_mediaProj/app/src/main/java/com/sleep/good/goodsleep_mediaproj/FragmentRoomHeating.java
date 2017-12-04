package com.sleep.good.goodsleep_mediaproj;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by eelhea on 2016-12-03.
 */

public class FragmentRoomHeating extends Fragment {

    ImageView room_iv;
    TextView tv_room_model_name;
    TextView tv_show_room_temp;
    TextView tv_add_room_model_num;

    ToggleButton btn_room_manual;

    Button btn_temp_up;
    Button btn_temp_down;


    String turnOn="no";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        onRestoreInstanceState(savedInstanceState);
        if(User.getInstance().getRoomModelNum()==null){
            view = inflater.inflate(R.layout.frag_room_heating_inactive, container, false);
            tv_add_room_model_num = (TextView)view.findViewById(R.id.tv_add_room_model_num);
            tv_add_room_model_num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), SetModelNumActivity.class);
                    intent.putExtra("model", "room");
                    startActivity(intent);
                }
            });
        } else {
            view = inflater.inflate(R.layout.frag_room_heating_active, container, false);

            btn_room_manual = (ToggleButton)view.findViewById(R.id.btn_room_manual);
            btn_room_manual.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(btn_room_manual.isChecked()){ //자동 모드로 설정
                        btn_room_manual.setBackgroundColor(Color.parseColor("#f3acab"));
                        btn_room_manual.setTextColor(Color.WHITE);
                    } else { //수동 모드로 설정
                        btn_room_manual.setBackgroundResource(R.drawable.pink_box);
                        btn_room_manual.setTextColor(Color.parseColor("#f3acab"));
                    }
                }
            });

            tv_room_model_name = (TextView)view.findViewById(R.id.tv_room_model_name);
            tv_room_model_name.setText(User.getInstance().getRoomModelNum());

            room_iv = (ImageView)view.findViewById(R.id.room_iv);

            tv_show_room_temp = (TextView)view.findViewById(R.id.tv_show_room_temp);
            tv_show_room_temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tv_show_room_temp.getText().toString().equals("OFF")){
                        tv_show_room_temp.setText("26 ºC");
                        room_iv.setImageResource(R.drawable.temper);
                        turnOn="yes";
                    } else {
                        tv_show_room_temp.setText("OFF");
                        room_iv.setImageResource(R.drawable.temper_no);
                        turnOn="no";
                    }
                }
            });

            btn_temp_up = (Button)view.findViewById(R.id.btn_temp_up);
            btn_temp_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String temp_temp = tv_show_room_temp.getText().toString().substring(0,2);
                    int temp_int = Integer.parseInt(temp_temp);
                    temp_int = temp_int + 1;
                    tv_show_room_temp.setText(temp_int+" ºC");
                }
            });
            btn_temp_down = (Button)view.findViewById(R.id.btn_temp_down);
            btn_temp_down.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String temp_temp = tv_show_room_temp.getText().toString().substring(0,2);
                    int temp_int = Integer.parseInt(temp_temp);
                    temp_int = temp_int - 1;
                    tv_show_room_temp.setText(temp_int+" ºC");
                }
            });

            if(turnOn.equals("yes")){
                tv_show_room_temp.setText("26 ºC");
                room_iv.setImageResource(R.drawable.temper);
            }else{
                tv_show_room_temp.setText("OFF");
                room_iv.setImageResource(R.drawable.temper_no);
            }
        }
        return view;
    }
    //Here you can restore saved data in onSaveInstanceState Bundle
    private void onRestoreInstanceState(Bundle savedInstanceState){
        if(savedInstanceState!=null){
            turnOn = savedInstanceState.getString("turnOn");
        }
    }

    //Here you Save your data
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("turnOn", turnOn);
    }
}
