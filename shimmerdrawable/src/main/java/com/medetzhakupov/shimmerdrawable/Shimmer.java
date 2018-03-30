package com.medetzhakupov.shimmerdrawable;

import android.support.annotation.ColorInt;

public interface Shimmer {

    int getPaddingLeft();

    int getPaddingRight();

    int getPaddingTop();

    int getPaddingBottom();

    @ColorInt
    int getColor();

    boolean isAnimate();

    @Gravity
    int getGravity();
}
