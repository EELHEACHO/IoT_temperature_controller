package com.sleep.good.goodsleep_mediaproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by eelhea on 2016-12-05.
 */

public class UserInfoActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton icBackIcon;

    TextView tv_user_name;
    TextView tv_user_address;

    Button btn_add_address;
    Button btn_add_modelNum;

    TextView tv_room_model_num;
    TextView tv_floor_model_num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity);

        toolbar = (Toolbar)findViewById(R.id.user_info_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        icBackIcon = (ImageButton)findViewById(R.id.icBackIcon);
        icBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, ProjectMainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rightin, R.anim.leftout);
                finish();
            }
        });

        tv_user_name = (TextView)findViewById(R.id.tv_user_name);
        if(User.getInstance().getName() != null){
            tv_user_name.setText(User.getInstance().getName());
        } else {
            tv_user_name.setText("사용자명이 없습니다.");
        }

        tv_user_address = (TextView)findViewById(R.id.tv_user_address);
        if(User.getInstance().getAddress() != null){
            tv_user_address.setText(User.getInstance().getAddress());
        } else {
            tv_user_address.setText("등록된 주소가 없습니다. 주소를 등록해주세요.");
        }

        btn_add_address = (Button)findViewById(R.id.btn_add_address);
        btn_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, SetAddressActivity.class);
                startActivity(intent);
            }
        });

        btn_add_modelNum = (Button)findViewById(R.id.btn_add_modelNum);
        btn_add_modelNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, SetModelNumActivity.class);
                startActivity(intent);
            }
        });

        tv_room_model_num = (TextView)findViewById(R.id.tv_room_model_num);
        if(User.getInstance().getRoomModelNum()!=null){
            tv_room_model_num.setText(User.getInstance().getRoomModelNum().toString());
        } else {
            tv_room_model_num.setText("난방 모델명을 등록해주세요.");
        }
        tv_floor_model_num = (TextView)findViewById(R.id.tv_floor_model_num);
        if(User.getInstance().getFloorModelNum()!=null){
            tv_floor_model_num.setText(User.getInstance().getFloorModelNum().toString());
        } else {
            tv_floor_model_num.setText("매트 모델명을 등록해주세요.");
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserInfoActivity.this, ProjectMainActivity.class);
        startActivity(intent);
    }
}
