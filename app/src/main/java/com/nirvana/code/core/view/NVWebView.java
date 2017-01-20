package com.nirvana.code.core.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.nirvana.code.BuildConfig;

/**
 * Created by kriszhang on 16/6/3.
 */
public class NVWebView extends WebView implements VerticalLinearLayout.OnPageChangeListener{
    public NVWebView(Context context) {
        super(context);
        initWebview();
    }

    public NVWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebview();

    }

    public NVWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWebview();

    }

    public class NVDownloadListener implements DownloadListener{

        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Uri uri=Uri.parse(url);
            Intent intent=new Intent(Intent.ACTION_VIEW,uri);
            getContext().startActivity(intent);
        }
    }

    /**
     * 设置默认下载
     * 下载主要有两种方式:
     * 1.自定义下载
     * 2.调用系统默认下载模块
     */
    public void setNVDownloadListener(){
        setDownloadListener(new NVDownloadListener());
    }

    /**
     * 初始化webview
     */
    public void initWebview(){
        WebSettings settings=this.getSettings();
        String ua = settings.getUserAgentString();
        settings.setUserAgentString(ua+"; Android;AppInstalled==1");
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);//根据cache-control决定是否从网络上取数据
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(false);//将图片调整到适合webview的大小
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//支持内容重新布局
        settings.setAllowFileAccess(true);//设置可以访问文件
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        settings.setLoadWithOverviewMode(true);//缩放至屏幕的大小
        if (Build.VERSION.SDK_INT >= 19){
            settings.setLoadsImagesAutomatically(true);
        }else {
            settings.setLoadsImagesAutomatically(false);
        }
        setLongClickable(false);
        settings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (BuildConfig.DEBUG) {
                setWebContentsDebuggingEnabled(true);
            }
        }
        setNVDownloadListener();

    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        if (getContentHeight()>400){
//
//        }
//        return false;
        return super.onInterceptTouchEvent(ev);
    }

    float mlastY=0;
    public boolean mIntercept=false;
    public boolean isUp=false;
    boolean isBottom;
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
////
//
//
//
//
////        Log.d("NVWebview","getY="+event.getY()+",mlastY"+mlastY);
////        if (getY()-mlastY>100){
////            mlastY=event.getY();
////            return false;
////        }
////        mIntercept=true;
//
//        final int action = event.getAction();
//        switch(action & MotionEvent.ACTION_MASK){
//            case MotionEvent.ACTION_DOWN:
//                mlastY = event.getY();
////                if (isBottom)
////                return false;
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                final int y = (int )event.getY();
//
//                if((y -mlastY)>50) { // 向下
//
//                    Log.d("NV","向下滑");
//                    isUp=false;
//                }else{ // 向上
//                    Log.d("NV","向上滑");
//                    isUp=true;
//                    if (getContentHeight()* getScale() -( getHeight()+ getScrollY())==0){//webview已经滑动到底部
//                        Log.d("NV","滑到低部了");
////                        Toast.makeText(getContext(),"滑到底部了",Toast.LENGTH_SHORT).show();
////                         requestDisallowInterceptTouchEvent(true);
//                        isBottom=true;
////                        mIntercept=false;
////                        return mIntercept;
//                    }else {
////                    getParent().requestDisallowInterceptTouchEvent(true);
//                        isBottom=false;
//                    }
//
//               }
//                mlastY = event.getY();
//                break;
//
//            case MotionEvent.ACTION_UP:
//
//                mlastY = event.getY();
//                break;
//
//        }
////        if (isUp && isBottom){
////            Log.d("NV2","isUp="+isUp+",isBottom="+isBottom);
////            return false;
////        }
//        Log.d("NV","isUp="+isUp+",isBottom="+isBottom);
//        return super.onTouchEvent(event);
//    }


    public void reset(){
//        isUp=false;
//        isBottom=false;
        Log.d("NV","reset");
    }

    @Override
    public void onPageChange(int currentPage) {
        isUp=false;
    }
}
