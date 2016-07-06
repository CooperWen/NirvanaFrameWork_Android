package com.nirvana.code;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.nirvana.code.core.view.NVWebView;

public class WebViewActivity extends Activity {

    private NVWebView webView;
    private ViewGroup mRootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view);
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
        webView=(NVWebView) findViewById(R.id.webview);
        mRootView=(ViewGroup) findViewById(R.id.root_view);
        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http:/www.haowuyun.com/mblog/index");
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
//                int webviewContentHeight=webView.getContentHeight();
//                ViewGroup.LayoutParams layoutParams=webView.getLayoutParams();
//                layoutParams.height=webviewContentHeight;
//                webView.setLayoutParams(layoutParams);
//                Toast.makeText(getApplicationContext(),"webview height="+webviewContentHeight,Toast.LENGTH_SHORT).show();
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
