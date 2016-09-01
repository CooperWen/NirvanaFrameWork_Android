package com.nirvana.code;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by kriszhang on 16/7/6.
 */
public class NivanaApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
