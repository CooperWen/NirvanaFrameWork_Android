package com.nirvana.code.utils;

import android.content.Context;

/**
 * Created by kriszhang on 2017/1/31.
 */

public class NVUtils {
    public static int dip2pix(Context context, int dips) {
        int densityDpi = context.getResources().getDisplayMetrics().densityDpi;
        return (dips * densityDpi) / 160;
    }

    public static int pix2dip(Context context, int pixs) {
        int densityDpi = context.getResources().getDisplayMetrics().densityDpi;
        return (pixs * 160) / densityDpi;
    }

}
