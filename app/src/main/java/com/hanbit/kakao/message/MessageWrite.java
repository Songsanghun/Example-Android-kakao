package com.hanbit.kakao.message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MessageWrite extends AppCompatActivity {
    String temp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Context context = MessageWrite.this;
        LinearLayout frame = new LinearLayout(context);
        LinearLayout.LayoutParams mm = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        LinearLayout.LayoutParams mw = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        frame.setLayoutParams(mm);
        Intent intent = this.getIntent();
        String idAndPhone = intent.getExtras().toString();
        Log.d("넘어온 ID,이름,전화번호:",idAndPhone);
        WebView wv = new WebView(context);
        wv.setLayoutParams(mm);
        WebSettings settings = wv.getSettings();
        settings.setUseWideViewPort(true);    //HTML을 가능하게함   줌 기능 추가
        settings.setJavaScriptEnabled(true);  //javaScript 가능하게함
        wv.setWebViewClient(new WebViewClient());

        wv.addJavascriptInterface(new JavascriptInterface() {
            @Override @android.webkit.JavascriptInterface //반드시 필요.
            public void showToast(String message) {
                Toast.makeText(context,temp,Toast.LENGTH_LONG).show();
            }

            @Override @android.webkit.JavascriptInterface
            public void sendMessage(String message) {
                temp = message;
            }
        }, "Hydrid");
        wv.loadUrl("file:///android_asset/www/html/messageWrite.html");
        frame.addView(wv);
        setContentView(frame);

        /*setContentView(R.layout.activity_message_write);*/
    }
    // proxy pattern
    public interface JavascriptInterface{
        public void showToast(String message);
        public void sendMessage(String message);
    }
}
