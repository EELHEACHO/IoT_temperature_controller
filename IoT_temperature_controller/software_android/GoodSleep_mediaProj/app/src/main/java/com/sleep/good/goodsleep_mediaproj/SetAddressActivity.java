package com.sleep.good.goodsleep_mediaproj;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by eelhea on 2016-12-04.
 */

public class SetAddressActivity extends AppCompatActivity {

    private WebView webView;
    private TextView tv_postcode;
    private TextView tv_address;
    private Handler handler;
    Button btn_ok_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_address_activity);

        tv_postcode = (TextView)findViewById(R.id.tv_postcode);
        tv_address = (TextView)findViewById(R.id.tv_address);
        btn_ok_address = (Button)findViewById(R.id.btn_ok_address);

        // WebView 초기화
        init_webView();

        // 핸들러를 통한 JavaScript 이벤트 반응
        handler = new Handler();

        btn_ok_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetAddressActivity.this, SetConfirmAddressActivity.class);
                intent.putExtra("postcode", tv_postcode.getText().toString());
                intent.putExtra("address", tv_address.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void init_webView() {
        // WebView 설정
        webView = (WebView) findViewById(R.id.webView);
        // JavaScript 허용
        webView.getSettings().setJavaScriptEnabled(true);
        // JavaScript의 window.open 허용
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        // JavaScript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        // 두 번째 파라미터는 사용될 php에도 동일하게 사용해야함
        webView.addJavascriptInterface(new AndroidBridge(), "TestApp");
        // web client 를 chrome 으로 설정
        webView.setWebChromeClient(new WebChromeClient());
        // webview url load
        webView.loadUrl("http://honeyjam.ivyro.net/AddAddress.php");
    }

    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tv_postcode.setText(String.format("(%s)", arg1));
                    tv_address.setText(String.format("%s %s", arg2, arg3));
                    // WebView를 초기화 하지않으면 재사용할 수 없음
                    init_webView();
                }
            });
        }
    }
}
