package com.example.cs480projectteam3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class web extends Activity  {

    private Button Course_Dscrip;
    private Button Calender;
    private Button Back;
    private WebView webView;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.webview);
        Course_Dscrip = (Button)findViewById(R.id.Course_discrip);
        Calender = (Button)findViewById(R.id.calender);
        Back = (Button)findViewById(R.id.Back);
        webView = (WebView)findViewById(R.id.webview);

                 Course_Dscrip.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         String urlText = "https://bentleyapps.azurewebsites.net/course-listing/index.php";
                         webView.loadUrl(urlText);
                     }
                 });
                 Calender.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         String UrlText = "https://www.bentleyspeak.com/student-orgs-2";
                         webView.loadUrl(UrlText);
                     }
                 });
                 Back.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         finish();
                     }
                 });




    }
}