package com.medetzhakupov.shimmerdrawable;

import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ShimmerRect extends BaseShimmer {

    private final int height;

    public ShimmerRect(int height, @ColorInt int color, @ShimmerElement.Gravity int gravity, boolean animate) {
        super(color, gravity, animate);
        this.height = height;
    }

    public ShimmerRect(int height, @ColorInt int color, @ShimmerElement.Gravity int gravity) {
        super(color, gravity);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}
