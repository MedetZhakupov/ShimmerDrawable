package com.medetzhakupov.shimmerdrawable;

import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.view.Gravity.BOTTOM;
import static android.view.Gravity.LEFT;
import static android.view.Gravity.RIGHT;
import static android.view.Gravity.TOP;

public abstract class BaseShimmer implements Shimmer {
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    private final @ColorInt int color;
    private final boolean animate;
    private final @Gravity int gravity;

    public BaseShimmer(@ColorInt int color, @ShimmerElement.Gravity int gravity, boolean animate) {
        this.color = color;
        this.animate = animate;
        this.gravity = gravity;
    }

    public BaseShimmer(@ColorInt int color, @ShimmerElement.Gravity int gravity) {
        this.color = color;
        this.animate = true;
        this.gravity = gravity;
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    public @Gravity
    int getGravity() {
        return gravity;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public @ColorInt
    int getColor() {
        return color;
    }

    public boolean isAnimate() {
        return animate;
    }

}
