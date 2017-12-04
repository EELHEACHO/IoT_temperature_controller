package com.sleep.good.goodsleep_mediaproj;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by eelhea on 2016-12-05.
 */

public class AlarmRecommendDialog extends Dialog {

    Context context = getContext();
    Button btn_alarm_ok;
    Button btn_alarm_cancel;

    public AlarmRecommendDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.alarm_recommend_dialog);


    }


}
