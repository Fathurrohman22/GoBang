package id.gobang.app.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import id.gobang.app.Helper.Bantuan;
import id.gobang.app.R;

public class Lacak extends AppCompatActivity {

    WebView webView;
    private Context context = Lacak.this;
    private ImageView ivClose;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lacak);
        webView = findViewById(R.id.webViewLacak);
        ivClose = findViewById(R.id.ivClose);

        final SweetAlertDialog loading = new Bantuan(context).swal_loading("Mohon tunggu beberapa saat");
        loading.show();


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://posindonesia.co.id/id/tracking");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loading.dismissWithAnimation();
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
