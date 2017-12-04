package com.sleep.good.goodsleep_mediaproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by eelhea on 2016-12-05.
 */

public class ShowDataActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton icOptionIcon;

    DataSleepFragment dataSleepFragment;
    DataFloorFragment dataFloorFragment;
    DataRoomFragment dataRoomFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_data_activity);

        toolbar = (Toolbar)findViewById(R.id.data_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        dataSleepFragment = new DataSleepFragment();
        dataFloorFragment = new DataFloorFragment();
        dataRoomFragment = new DataRoomFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.data_container, dataSleepFragment).commit();

        TabLayout tabs = (TabLayout)findViewById(R.id.data_tabs);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if(position==0){
                    selected = dataSleepFragment;
                } else if(position==1){
                    selected = dataRoomFragment;
                } else if(position==2){
                    selected = dataFloorFragment;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.data_container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        icOptionIcon = (ImageButton)findViewById(R.id.icOptionIcon);
        icOptionIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowDataActivity.this, UserInfoActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.leftin, R.anim.rightout);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ShowDataActivity.this, ProjectMainActivity.class);
        startActivity(intent);
    }
}
