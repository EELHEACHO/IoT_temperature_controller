package com.sleep.good.goodsleep_mediaproj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by eelhea on 2016-12-05.
 */

public class SetRoomModelNumFrag extends Fragment {

    EditText et_room_model_num;
    Button btn_set_room_model_num;
    Button btn_edit_room_model_num;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_model_room_frag, container, false);

        btn_set_room_model_num = (Button)view.findViewById(R.id.btn_set_room_model_num);
        btn_edit_room_model_num = (Button)view.findViewById(R.id.btn_edit_room_model_num);

        et_room_model_num = (EditText)view.findViewById(R.id.et_room_model_num);
        if(User.getInstance().getRoomModelNum()!=null){
            btn_set_room_model_num.setBackgroundColor(Color.parseColor("#d7d7d7"));
            btn_set_room_model_num.setEnabled(false);
            et_room_model_num.setText(User.getInstance().getRoomModelNum().toString());
        } else {
            btn_edit_room_model_num.setBackgroundColor(Color.parseColor("#d7d7d7"));
            btn_edit_room_model_num.setEnabled(false);
        }


        btn_edit_room_model_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modelNum = et_room_model_num.getText().toString();
                if(modelNum.equals("")){
                    Toast.makeText(getActivity(), "난방 모델명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    User.getInstance().setRoomModelNum(et_room_model_num.getText().toString());
                    Toast.makeText(getActivity(), "난방 모델명이 수정되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), ProjectMainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btn_set_room_model_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modelNum = et_room_model_num.getText().toString();
                if(modelNum.equals("")){
                    Toast.makeText(getActivity(), "난방 모델명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    User.getInstance().setRoomModelNum(et_room_model_num.getText().toString());
                    Toast.makeText(getActivity(), "난방 모델명이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), ProjectMainActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}
