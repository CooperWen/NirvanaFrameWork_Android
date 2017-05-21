package com.nirvana.code;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.githang.statusbar.StatusBarCompat;
import com.nirvana.code.core.base.BaseActivity;
import com.nirvana.code.core.view.NVWebView;
import com.nirvana.code.core.view.RoundProgressBar;
import com.umeng.socialize.utils.Log;

public class WebViewActivity extends BaseActivity {

    private NVWebView webView;
    private ViewGroup mRootView;
    private RoundProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_view);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.backgoud3) );
        String url=getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(url)){
            url="http:/www.haowuyun.com/index";
        }
        progressBar=(RoundProgressBar)findViewById(R.id.roundProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        webView=(NVWebView) findViewById(R.id.webview);
        webView.initWebview();
        mRootView=(ViewGroup) findViewById(R.id.root_view);
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                if (url.startsWith("https://detail.m.tmall.com/item.htm")){
                    String url11 = "tmall://tmallclient/?{\"action\":”item:id=542847657165”}";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    finish();
                }else {
                    view.loadUrl(url);
                }
                return false;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (progressBar.getProgress()==progressBar.getMax()){
                    progressBar.setVisibility(View.GONE);
                }
//                int webviewContentHeight=webView.getContentHeight();
//                ViewGroup.LayoutParams layoutParams=webView.getLayoutParams();
//                layoutParams.height=webviewContentHeight;
//                webView.setLayoutParams(layoutParams);
//                Toast.makeText(getApplicationContext(),"webview height="+webviewContentHeight,Toast.LENGTH_SHORT).show();
            }
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view,
                                                              WebResourceRequest url) {
                // TODO Auto-generated method stub
                Log.e("Splash","url="+url.toString());
                if (url != null && url.toString().startsWith("http://")
                        || url.toString().startsWith("https://")) {
                    if ((url.toString().startsWith("http://www.haowuyun.com/store/orig/") ||url.toString().startsWith("http://www.haowuyun.com/store/thumbs/")) && (url.toString().endsWith(".png") || url.toString().endsWith(".jpg") || url.toString().endsWith(".jpeg") || url.toString().endsWith(".JPEG"))){
//                        firstPicUrl=url.toString();
//                        Log.e("Splash","firstPicUrl="+firstPicUrl);
                    }
                    return super.shouldInterceptRequest(view, url);
                } else {
                    try {
                        // 不支持的跳转协议调用外部组件处理
                        Intent in = new Intent(Intent.ACTION_VIEW, Uri
                                .parse(url.toString()));
                        startActivity(in);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    return null;
                }
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if (progressBar.getProgress()==progressBar.getMax()){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.removeCallbacks(null);
        mRootView.removeView(webView);
        webView.clearCache(true);
        webView.destroy();
    }
}
