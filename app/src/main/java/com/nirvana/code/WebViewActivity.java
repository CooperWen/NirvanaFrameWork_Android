package com.nirvana.code;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nirvana.code.core.view.NVWebView;
import com.nirvana.code.core.view.RoundProgressBar;

public class WebViewActivity extends Activity {

    private NVWebView webView;
    private ViewGroup mRootView;
    private RoundProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.news_view);
//        KCRequestMgr mKcRequestMgr = KCRequestMgr.getKCRequestMgr(getBaseContext());
////        url = appendCommonParameters(url);
//        mKcRequestMgr.getRequest("https://www.baidu.com", new KCListener.Listener<String>() {
//            @Override
//            public void onDataReturned(String s, String s2) {
//                Log.d("WebViewActivity",s+",s2="+s2);
//            }
//
//            @Override
//            public void onRequestError(String s, KCError kcError) {
//
//                Log.d("WebViewActivity","onRequestError: "+s+",kcError="+kcError);
//            }
//        });
        String url=getIntent().getStringExtra("url");
        if (TextUtils.isEmpty(url)){
            url="http:/www.haowuyun.com/index";
        }
        progressBar=(RoundProgressBar)findViewById(R.id.roundProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        webView=(NVWebView) findViewById(R.id.webview);
        mRootView=(ViewGroup) findViewById(R.id.root_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(url);
        webView.setLongClickable(false);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
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
