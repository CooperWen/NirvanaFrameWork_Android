package com.nirvana.code;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;
import com.nirvana.code.core.view.particle.ParticleView;

public class FirstActivity extends Activity {
    private ParticleView mParticleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.first_acrivity);
        mParticleView=(ParticleView)findViewById(R.id.paticle_view);
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
                finish();
            }
        },3000);

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
