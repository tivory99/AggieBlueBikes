package com.example.tylerivory.aggiebluebikes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by crlsktr on 11/17/2017.
 */

public class ABBFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abb_feed);
        WebView feed = (WebView)findViewById(R.id.ABBfeedWebView);
        feed.setWebViewClient(new WebViewClient());
        feed.getSettings().setJavaScriptEnabled(true);
        //feed.loadUrl("https://touch.facebook.com/AggieBlueBikes");
        String feedhtml = "<iframe src=\"https://www.facebook.com/plugins/page.php?href=https%3A%2F%2Fwww.facebook.com%2Faggiebluebikes&tabs=timeline&width=340&height=500&small_header=true&adapt_container_width=true&hide_cover=false&show_facepile=false&appId\" width=\"parent\" height=\"100%\" style=\"border:none;overflow:hidden\" scrolling=\"no\" frameborder=\"0\" allowTransparency=\"true\"></iframe>";
        feed.loadData(feedhtml,"text/html",null);
    }
}
