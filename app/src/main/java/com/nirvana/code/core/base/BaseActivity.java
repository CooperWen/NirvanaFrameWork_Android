package com.nirvana.code.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;


import com.nirvana.code.widgets.swipeback.PrefUtil;
import com.nirvana.code.widgets.swipeback.SwipeBackLayout;


/**
 * Created by laucherish on 16/3/15.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected SwipeBackLayout mSwipeBackLayout;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(PrefUtil.getThemeRes());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
