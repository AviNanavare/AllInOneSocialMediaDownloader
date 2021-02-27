package com.example.allinonedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class FifthActivity extends AppCompatActivity {

    private WebView webTwitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        webTwitter = (WebView)findViewById(R.id.webTwitter);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        webTwitter.setWebViewClient(new WebViewClient());
        webTwitter.loadUrl("https://www.savetweetvid.com/");

        WebSettings webSettings = webTwitter.getSettings();
        webTwitter.getSettings().setJavaScriptEnabled(true);
        webTwitter.setWebViewClient(new WebViewClient());

        //Improve webview performance
        webTwitter.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webTwitter.getSettings().setAppCacheEnabled(true);
        webTwitter.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSaveFormData(true);

        //External storage permission for saving file
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M)
        {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
            {
                Log.d("permission","Permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions , 1);
            }
        }

        //handling download
        webTwitter.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mineType, long contentLength) {
                DownloadManager.Request req = new DownloadManager.Request(Uri.parse(url));
                req.setMimeType(mineType);
                String cookies = CookieManager.getInstance().getCookie(url);
                req.addRequestHeader("cookie",cookies);
                req.addRequestHeader("User-Agent" ,userAgent);
                req.setDescription("Downloading File . . .");
                req.setTitle(URLUtil.guessFileName(url,contentDisposition,mineType));
                req.allowScanningByMediaScanner();
                req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                req.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,URLUtil.guessFileName(url,contentDisposition,mineType));
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(req);
                Toast.makeText(getApplicationContext(),"Downloading File",Toast.LENGTH_SHORT).show();
            }
        });
    }
}