package com.medetzhakupov.shimmerdrawable;

import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by MEDETZ on 12/7/2017.
 */

public class ShimmerElement {
    public static final int TOP = 0;
    public static final int BOTTOM = 1;

    @IntDef ({ TOP, BOTTOM })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gravity{}

    private final int height;
    private final int paddingLeft;
    private final int paddingRight;
    private final @ColorInt int color;
    private final boolean animate;
    private final @Gravity int gravity;

    public ShimmerElement(int height, int paddingLeft, int paddingRight, @ColorInt int color, @Gravity int gravity, boolean animate) {
        this.height = height;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.color = color;
        this.animate = animate;
        this.gravity = gravity;
    }

    public ShimmerElement(int height, int paddingLeft, int paddingRight, @ColorInt int color, @Gravity int gravity) {
        this.height = height;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        this.color = color;
        this.animate = true;
        this.gravity = gravity;
    }

    public @Gravity int getGravity() {
        return gravity;
    }

    public int getHeight() {
        return height;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public @ColorInt int getColor() {
        return color;
    }

    public boolean isAnimate() {
        return animate;
    }
}
