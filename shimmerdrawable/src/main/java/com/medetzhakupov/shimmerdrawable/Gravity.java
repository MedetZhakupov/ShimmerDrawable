package com.medetzhakupov.shimmerdrawable;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.medetzhakupov.shimmerdrawable.Gravity.BOTTOM;
import static com.medetzhakupov.shimmerdrawable.Gravity.LEFT;
import static com.medetzhakupov.shimmerdrawable.Gravity.RIGHT;
import static com.medetzhakupov.shimmerdrawable.Gravity.TOP;

@IntDef({TOP, BOTTOM, LEFT, RIGHT})
@Retention(RetentionPolicy.SOURCE)
public @interface Gravity {
    int TOP=0;
    int BOTTOM=1;
    int LEFT=2;
    int RIGHT=3;
}
