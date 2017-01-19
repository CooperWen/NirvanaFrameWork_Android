package com.nirvana.code;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.nirvana.code.core.view.particle.ParticleView;

public class FirstActivity extends Activity {
    private ParticleView mParticleView;
    private ImageView mAdLaout;
    private boolean adClicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.first_acrivity);
        mParticleView=(ParticleView)findViewById(R.id.paticle_view);
        mAdLaout=(ImageView) findViewById(R.id.splash_ad);
        mAdLaout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adClicked=true;
            }
        });
        setAdImgBg();

        mParticleView.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
            @Override
            public void onAnimationEnd() {

            }
        });
        mParticleView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mParticleView.startAnim();
            }
        },200);
        new Handler(getMainLooper()).postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent=new Intent(FirstActivity.this,Splash.class);
                startActivity(intent);
                if (adClicked){
                    gotoAdDetial();
                }
                finish();
            }
        },3000);

    }

    public void gotoAdDetial(){
        Intent intent=new Intent(FirstActivity.this,WebViewActivity.class);
        intent.putExtra("url","https://s.click.taobao.com/x7DIABx");
        startActivity(intent);
    }

    public void setAdImgBg(){
        ImageRequest imageRequest = new ImageRequest(
                "http://www.haowuyun.com/pics/app_splash.jpg_400x400_.webp",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        mAdLaout.setVisibility(View.VISIBLE);
                        mAdLaout.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mAdLaout.setVisibility(View.GONE);
            }
        });
        RequestQueue mQueue = Volley.newRequestQueue(FirstActivity.this);
        mQueue.add(imageRequest);
    }
    @Override
    protected void onResume() {
        super.onResume();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
