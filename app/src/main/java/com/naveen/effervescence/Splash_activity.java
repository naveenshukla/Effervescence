package com.naveen.effervescence;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

public class Splash_activity extends Activity {
    WebView mWebView;
    private static int SPLASH_TIME_OUT = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setBackgroundColor(Color.TRANSPARENT);
        //String base = Environment.getExternalStorageDirectory().getAbsolutePath().toString();
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<title></title>\n" +
                "<style>\t\n" +
                "div{\n" +
                "\tbackground-image: inherit;\n" +
                "\theight: 90px;\n" +
                "\tline-height: 90px;\n" +
                "\tfont-family: sans-serif;\n" +
                "\tfont-size: 35px;\n" +
                "\ttext-align: center;\n" +
                "\tvertical-align: middle;  \n" +
                "    -webkit-animation: color-change 4s infinite;\n" +
                "    -moz-animation: color-change 4s infinite;\n" +
                "    -o-animation: color-change 4s infinite;\n" +
                "    -ms-animation: color-change 4s infinite;\n" +
                "    animation: color-change 4s infinite;\n" +
                "}\n" +
                "\n" +
                "@-webkit-keyframes color-change {\n" +
                "    0% { color: white; }\n" +
                "    50% { color: red; }\n" +
                "    70% {color: blue;}\n" +
                "    100% { color: white; }\n" +
                "}\n" +
                "@-moz-keyframes color-change {\n" +
                "   0% { color: white; }\n" +
                "    50% { color: red; }\n" +
                "    70% {color: blue;}\n" +
                "    100% { color: white; }\n" +
                "}\n" +
                "@-ms-keyframes color-change {\n" +
                "     0% { color: white; }\n" +
                "    50% { color: red; }\n" +
                "    70% {color: blue;}\n" +
                "    100% { color: white; }\n" +
                "}\n" +
                "@-o-keyframes color-change {\n" +
                "     0% { color: white; }\n" +
                "    50% { color: red; }\n" +
                "    70% {color: blue;}\n" +
                "    100% { color: white; }\n" +
                "}\n" +
                "@keyframes color-change {\n" +
                "     0% { color: white; }\n" +
                "    50% { color: red; }\n" +
                "    70% {color: blue;}\n" +
                "    100% { color: white; }\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"idea\">Effervescence'16</div>\n" +
                "</body>\n" +
                "</html>";
        mWebView.loadDataWithBaseURL("", html, "text/html","utf-8", "");
        //SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        //String name = pref.getString("Name",null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("hello","splash screen is running");
                Intent i = new Intent(Splash_activity.this, Menu.class);
                Log.d("hello","intent is created");
                startActivity(i);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
