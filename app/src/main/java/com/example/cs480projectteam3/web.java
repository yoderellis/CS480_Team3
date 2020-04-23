package com.example.cs480projectteam3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class web extends Activity  {

    private Button Course_Dscrip;
    private Button Calendar;
    private Button Back;
    private WebView webView;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.webview);
        Course_Dscrip = findViewById(R.id.Course_discrip);
        Calendar = findViewById(R.id.calendar);
        Back = findViewById(R.id.Back);
        webView = findViewById(R.id.webview);

        // links clicked in the WebView open in WebView
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest req) {
                view.loadUrl(req.getUrl().toString());
                return true;
            }
        });

        // what happens when the course descriptions button is clicked
        Course_Dscrip.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String urlText = "https://www.bentley.edu/offices/registrar/course-descriptions";
                 webView.loadUrl(urlText);
            }
         });

        // what happens when the calendar button is clicked
        Calendar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String UrlText = "https://www.bentleyspeak.com/student-orgs-2";
                 webView.loadUrl(UrlText);
             }
         });

        // what happens when the back button is clicked
        Back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 finish();
             }
         });

    }
}