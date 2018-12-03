package com.example.hp.news_feed_task.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hp.news_feed_task.R;

public class NewsActivity extends AppCompatActivity {
    WebView postWebView;
    String myWebLink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        try {
            myWebLink = getIntent().getStringExtra("URL");
            Log.e("ULR", myWebLink);
        } catch (Exception e) {
            e.printStackTrace();
        }
        postWebView = (WebView) findViewById(R.id.postWebView);
        postWebView.setWebViewClient(new MyBrowser());
        postWebView.getSettings().setJavaScriptEnabled(true);
        postWebView.getSettings().setBuiltInZoomControls(true);
        postWebView.loadUrl(myWebLink);


        //
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void onBackPressed(){

        if(postWebView.canGoBack()){
            postWebView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }


}
