package com.example.dez.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;

public class MainActivity extends AppCompatActivity {
    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    webView = (WebView)this.findViewById(R.id.webview);

    webView.loadUrl("http://m.baidu.com");

    WebSettings settings = webView.getSettings();
    settings.setJavaScriptEnabled(true);

    webView.setWebViewClient(new WebViewClient(){
        @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            if(Uri.parse(url).getHost().equals("m.baidu.com")){

            return false;
        }
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
            startActivity(intent);
            return true;
        }
    });

    }
}
