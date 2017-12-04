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

public class SetFloorModelNumFrag extends Fragment {

    EditText et_floor_model_num;
    Button btn_set_floor_model_num;
    Button btn_edit_floor_model_num;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_model_floor_frag, container, false);

        btn_set_floor_model_num = (Button)view.findViewById(R.id.btn_set_floor_model_num);
        btn_edit_floor_model_num = (Button)view.findViewById(R.id.btn_edit_floor_model_num);

        et_floor_model_num = (EditText)view.findViewById(R.id.et_floor_model_num);
        if(User.getInstance().getFloorModelNum()!=null){
            btn_set_floor_model_num.setBackgroundColor(Color.parseColor("#d7d7d7"));
            btn_set_floor_model_num.setEnabled(false);
            et_floor_model_num.setText(User.getInstance().getFloorModelNum().toString());
        } else {
            btn_edit_floor_model_num.setBackgroundColor(Color.parseColor("#d7d7d7"));
            btn_edit_floor_model_num.setEnabled(false);
        }

        btn_edit_floor_model_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modelNum = et_floor_model_num.getText().toString();
                if(modelNum.equals("")){
                    Toast.makeText(getActivity(), "매트 모델명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    User.getInstance().setFloorModelNum(et_floor_model_num.getText().toString());
                    Toast.makeText(getActivity(), "매트 모델명이 수정되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), ProjectMainActivity.class);
                    startActivity(intent);
                }
            }
        });

        btn_set_floor_model_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modelNum = et_floor_model_num.getText().toString();
                if(modelNum.equals("")){
                    Toast.makeText(getActivity(), "매트 모델명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    User.getInstance().setFloorModelNum(et_floor_model_num.getText().toString());
                    Toast.makeText(getActivity(), "매트 모델명이 등록되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), ProjectMainActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}
