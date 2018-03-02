package com.medetzhakupov.shimmerdrawablesample;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by MEDETZ on 12/15/2017.
 */

class Util {
    static int convertDpToPixels(float dp, Context context) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return px;
    }
}
