package com.nirvana.code.core.utils.log;

import android.util.Log;

import com.nirvana.code.core.utils.config.BasicConfig;

/**
 * Created by kriszhang on 16/5/27.
 */
public class NVLog {
    public static void log_d(String tag,Object msg){
        if (BasicConfig.isDebug){
            Log.d(tag,String.valueOf(msg));
        }
    }

    public static void log_i(String tag,Object msg){
        if (BasicConfig.isDebug){
            Log.i(tag,String.valueOf(msg));
        }
    }

    public static void log_e(String tag,Object msg){
        if (BasicConfig.isDebug){
            Log.e(tag,String.valueOf(msg));
        }
    }
}
