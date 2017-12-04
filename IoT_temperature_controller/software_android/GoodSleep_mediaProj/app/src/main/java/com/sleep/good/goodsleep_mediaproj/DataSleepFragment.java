package com.sleep.good.goodsleep_mediaproj;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by eelhea on 2016-12-03.
 */

public class DataSleepFragment extends Fragment {

    public static final String ASSET_PATH = "file:///android_asset/";

    WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_sleep_data_frag, container, false);

        webView = (WebView)view.findViewById(R.id.webView_sleep);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/googlecharts_sleep.html");

        return view;
    }
}
