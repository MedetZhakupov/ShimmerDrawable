package com.medetzhakupov.shimmerdrawable;

import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by MEDETZ on 12/7/2017.
 */

public class ShimmerCircle extends BaseShimmer {

    private final float radius;

    public ShimmerCircle(float radius, @ColorInt int color, @Gravity int gravity, boolean animate) {
        super(color, gravity, animate);
        this.radius = radius;
    }

    public ShimmerCircle(float radius, @ColorInt int color, @Gravity int gravity) {
        super(color, gravity);
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }
}
