package com.sleep.good.goodsleep_mediaproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by eelhea on 2016-12-05.
 */

public class SetModelNumActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton icBackIcon;

    SetRoomModelNumFrag setRoomModelNumFrag;
    SetFloorModelNumFrag setFloorModelNumFrag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_model_num_activity);

        String model="";

        Intent intent = getIntent();
        if(intent.getExtras()!=null){
            model = intent.getExtras().getString("model");
        }

        toolbar = (Toolbar)findViewById(R.id.add_model_num_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setRoomModelNumFrag = new SetRoomModelNumFrag();
        setFloorModelNumFrag = new SetFloorModelNumFrag();

        TabLayout tabs = (TabLayout)findViewById(R.id.tabs);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if(position==0){
                    selected = setRoomModelNumFrag;
                } else if(position==1){
                    selected = setFloorModelNumFrag;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        if(model.equals("floor")){
            tabs.getTabAt(1).select();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, setFloorModelNumFrag).commit();
        } else if(model.equals("room")){
            getSupportFragmentManager().beginTransaction().replace(R.id.container, setRoomModelNumFrag).commit();
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.container, setRoomModelNumFrag).commit();
        }



        icBackIcon = (ImageButton)findViewById(R.id.icBackIcon);
        icBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetModelNumActivity.this, ProjectMainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rightin, R.anim.leftout);
                finish();
            }
        });
    }
}
