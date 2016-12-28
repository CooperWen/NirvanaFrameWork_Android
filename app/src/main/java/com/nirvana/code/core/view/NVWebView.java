package com.nirvana.code.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by kriszhang on 16/6/3.
 */
public class NVWebView extends WebView implements VerticalLinearLayout.OnPageChangeListener{
    public NVWebView(Context context) {
        super(context);
        String ua = this.getSettings().getUserAgentString();
        this.getSettings().setUserAgentString(ua+"; Android");
    }

    public NVWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        String ua = this.getSettings().getUserAgentString();
        this.getSettings().setUserAgentString(ua+"; Android");
    }

    public NVWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        String ua = this.getSettings().getUserAgentString();
        this.getSettings().setUserAgentString(ua+"; Android");
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
