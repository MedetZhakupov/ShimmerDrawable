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
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    @IntDef ({ TOP, BOTTOM, LEFT, RIGHT })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gravity{}

    public static final int CIRCLE = 0;
    public static final int RECTANGLE = 1;
    @IntDef ({ CIRCLE, RECTANGLE })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Shape{}

    private final int height;
    private int paddingLeft;
    private int paddingTop;
    private int paddingRight;
    private int paddingBottom;
    private final @ColorInt int color;
    private final boolean animate;
    private final @Gravity int gravity;
    private final @Shape int shape;


    public ShimmerElement(int height, @ColorInt int color, @Gravity int gravity, @Shape int shape, boolean animate) {
        this.height = height;
        this.color = color;
        this.animate = animate;
        this.gravity = gravity;
        this.shape = shape;
    }

    public ShimmerElement(int height, @ColorInt int color, @Gravity int gravity, @Shape int shape) {
        this.height = height;
        this.color = color;
        this.animate = true;
        this.gravity = gravity;
        this.shape = shape;
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

    public @Gravity int getGravity() {
        return gravity;
    }

    public @Shape int getShape() {
        return shape;
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
