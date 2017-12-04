package com.sleep.good.goodsleep_mediaproj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by eelhea on 2016-12-05.
 */

public class DataFloorFragment extends Fragment {

    WebView webView1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_floor_data_frag, container, false);


        webView1 = (WebView)view.findViewById(R.id.webView_floor);
        webView1.setVerticalScrollBarEnabled(false);
        webView1.setHorizontalScrollBarEnabled(false);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.loadUrl("file:///android_asset/googlecharts_floor.html");


        return view;
    }
}
