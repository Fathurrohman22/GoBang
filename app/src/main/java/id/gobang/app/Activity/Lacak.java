package id.gobang.app.Activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import id.gobang.app.R;

public class Lacak extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lacak);

        webView = (WebView) findViewById(R.id.webViewLacak);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.posindonesia.co.id/id/tracking");
        webView.setWebViewClient(new WebViewClient());
    }
}
