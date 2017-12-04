package com.sleep.good.goodsleep_mediaproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by eelhea on 2016-12-04.
 */

public class SetConfirmAddressActivity extends AppCompatActivity {

    TextView tv_postcode_fix;
    TextView tv_address_fix;
    String postcode;
    String address;
    EditText et_address_detail;

    Button btn_find_address;
    Button btn_set_address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_address_custom);

        Intent intent = getIntent();
        postcode = intent.getStringExtra("postcode");
        address = intent.getStringExtra("address");

        btn_find_address = (Button)findViewById(R.id.btn_find_address);
        btn_set_address = (Button)findViewById(R.id.btn_set_address);

        tv_postcode_fix = (TextView)findViewById(R.id.tv_postcode_fix);
        tv_address_fix = (TextView)findViewById(R.id.tv_address_fix);
        et_address_detail = (EditText)findViewById(R.id.et_address_detail);

        tv_postcode_fix.setText(postcode.toString());
        tv_address_fix.setText(address.toString());

        btn_set_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = tv_postcode_fix.getText().toString()+" "+tv_address_fix.getText().toString()+" "+et_address_detail.getText().toString();
                User.getInstance().setAddress(address);
                Toast.makeText(getApplicationContext(), "주소가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SetConfirmAddressActivity.this, ProjectMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
