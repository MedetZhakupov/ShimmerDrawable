package com.medetzhakupov.shimmerdrawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;

import java.util.List;

import static com.medetzhakupov.shimmerdrawable.Gravity.BOTTOM;
import static com.medetzhakupov.shimmerdrawable.Gravity.LEFT;
import static com.medetzhakupov.shimmerdrawable.Gravity.RIGHT;

/**
 * Created by MEDETZ on 11/30/2017.
 */

public class ShimmerDrawable extends Drawable implements Animatable, Runnable {

    private static final long FRAME_DELAY = 1000 / 60;
    private boolean running = false;
    private long startTime;
    private int duration = 1000;
    private int animationDelay = 300;
    private int[] colors = new int[]{Color.parseColor("#00FFFFFF"), Color.parseColor("#FFFFFFFF"), Color.parseColor("#00FFFFFF")};
    private Handler handler = new Handler(Looper.getMainLooper());

    private Interpolator interpolator = new AccelerateInterpolator();
    private Paint paint;
    private Paint backgroundPaint;
    private int roundedCorner = 0;
    private int paddingTop = 0;
    private int paddingBottom = 0;
    private int dividerPadding = 0;
    private final List<Shimmer> shimmerElementList;
    private final @ColorInt int[] backgroundColor;
    private final float[] colorSections;

    public ShimmerDrawable(@ColorInt int[] backgroundColor, float[] colorSections, List<Shimmer> shimmerElementList) {
        if (backgroundColor.length != colorSections.length) {
            throw new IllegalArgumentException("backgroundColor and colorSections must be a same length");
        }
        this.backgroundColor = backgroundColor;
        this.colorSections = colorSections;
        this.shimmerElementList = shimmerElementList;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setAnimationDelay(int animationDelay) {
        this.animationDelay = animationDelay;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    public ShimmerDrawable setRoundedCorner(int roundedCorner) {
        this.roundedCorner = roundedCorner;
        return this;
    }

    public ShimmerDrawable setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
        return this;
    }

    public ShimmerDrawable setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
        return this;
    }

    public ShimmerDrawable setDividerPadding(int dividerPadding) {
        this.dividerPadding = dividerPadding;
        return this;
    }

    private void init() {
        if (paint == null) {
            initPaint();
        }
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.WHITE);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.WHITE);
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        final Rect bounds = getBounds();

        final float elapsed = SystemClock.uptimeMillis() - startTime;
        final float rawProgress = elapsed / (float) duration;
        final float progress = interpolator.getInterpolation(rawProgress);
        final int save = canvas.save();

        if (isRunning()) {
            int width = bounds.width() + paddingTop + 200;
            paint.setShader(
                    new LinearGradient(
                            progress * width - 200, 0, progress * width, 100,
                            colors, null, Shader.TileMode.CLAMP));
        }

        //template
        for (int i = 0; i < backgroundColor.length; i++) {
            backgroundPaint.setColor(backgroundColor[i]);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawRoundRect(0, bounds.height() * colorSections[i], bounds.width(), bounds.height(), roundedCorner, roundedCorner, backgroundPaint);
                if (i > 0) {
                    float top = bounds.height() * colorSections[i];
                    canvas.drawRect(0, top, bounds.width(), top + roundedCorner, backgroundPaint);
                }
            } else {
                canvas.drawRect(0, bounds.height() * colorSections[i], bounds.width(), bounds.height(), backgroundPaint);
            }
        }

        int totalElementsHeightTop = 0;
        int totalElementsHeightBottom = 0;
        int topGravityElementCount = 0;
        int bottomGravityElementCount = 0;

        for (int i = 0; i < shimmerElementList.size(); i++) {
            Shimmer element = shimmerElementList.get(i);
            backgroundPaint.setColor(element.getColor());

            if (element.getGravity() == BOTTOM) {
                int bottom = bottomGravityElementCount > 0
                        ? bounds.height() - (totalElementsHeightBottom + (dividerPadding * bottomGravityElementCount))
                        : bounds.height();
                bottom = (bottom == bounds.height() || totalElementsHeightBottom == 0) ? bounds.height() - paddingBottom : bottom - paddingBottom;

                if (element instanceof ShimmerRect) {
                    ShimmerRect shimmerRect = (ShimmerRect) element;
                    drawRectShape(canvas, shimmerRect.getPaddingLeft(), bottom - shimmerRect.getHeight(),
                            bounds.width() - shimmerRect.getPaddingRight(), bottom);
                    totalElementsHeightBottom += shimmerRect.getHeight();
                } else if (element instanceof ShimmerCircle) {
                    ShimmerCircle shimmerCircle = (ShimmerCircle) element;
                    drawCircleShape(canvas,
                            shimmerCircle.getPaddingLeft() + shimmerCircle.getRadius(),
                            bottom - shimmerCircle.getRadius(),
                            shimmerCircle.getRadius());
                    totalElementsHeightBottom += 2 * shimmerCircle.getRadius();
                }

                bottomGravityElementCount++;
            } else if (element.getGravity() == ShimmerElement.TOP) {
                int top = topGravityElementCount > 0
                        ? (dividerPadding * topGravityElementCount) + totalElementsHeightTop
                        : 0;
                top = (top == 0 || totalElementsHeightTop == 0) ? paddingTop : top + paddingTop;

                if (element instanceof ShimmerRect) {
                    ShimmerRect shimmerRect = (ShimmerRect)element;
                    drawRectShape(canvas, shimmerRect.getPaddingLeft(),
                            top,
                            bounds.width() - shimmerRect.getPaddingRight(),
                            top + shimmerRect.getHeight());
                    totalElementsHeightTop += shimmerRect.getHeight();
                } else if (element instanceof ShimmerCircle) {
                    ShimmerCircle shimmerCircle = (ShimmerCircle)element;
                    drawCircleShape(canvas,
                            shimmerCircle.getPaddingLeft() + shimmerCircle.getRadius(),
                            top + shimmerCircle.getRadius(),
                            shimmerCircle.getRadius());
                    totalElementsHeightTop += 2 * shimmerCircle.getRadius();
                }

                topGravityElementCount++;
            }  else {
                throw new IllegalArgumentException("ShimmerElement.Gravity values must be used only");
            }
        }

        canvas.restoreToCount(save);
    }

    private void drawRectShape(final Canvas canvas, int left, int top, int right, int bottom) {
        Rect rect = new Rect(left, top, right, bottom);

        canvas.drawRect(rect, backgroundPaint);
        if (isRunning()) {
            canvas.drawRect(rect, paint);
        }
    }

    private void drawCircleShape(final Canvas canvas, float cx, float cy, float radius) {
        canvas.drawCircle(cx, cy, radius, backgroundPaint);
        if (isRunning()) {
            canvas.drawCircle(cx, cy, radius,  paint);
        }
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        init();
    }

    @Override
    public void setAlpha(int alpha) {
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    @Override
    public void start() {
        if (isRunning()) {
            stop();
        }
        running = true;
        startTime = SystemClock.uptimeMillis();
        invalidateSelf();
        scheduleSelf(this, startTime + FRAME_DELAY);
    }

    @Override
    public void stop() {
        unscheduleSelf(this);
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getIntrinsicHeight() {
        return 500;
    }

    @Override
    public int getIntrinsicWidth() {
        return 500;
    }

    @Override
    public void run() {
        invalidateSelf();
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis + FRAME_DELAY < startTime + duration) {
            scheduleSelf(this, uptimeMillis + FRAME_DELAY);
        } else {
            running = false;
            handler.postDelayed(this::start, animationDelay);
        }
    }
}
