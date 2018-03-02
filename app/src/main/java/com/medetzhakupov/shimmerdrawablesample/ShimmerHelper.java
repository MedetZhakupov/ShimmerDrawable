package com.medetzhakupov.shimmerdrawablesample;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.medetzhakupov.shimmerdrawable.ShimmerDrawable;
import com.medetzhakupov.shimmerdrawable.ShimmerElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MEDETZ on 12/15/2017.
 */

public class ShimmerHelper {
    Context context;

    public ShimmerHelper(Context context) {
        this.context = context;
    }

    public ShimmerDrawable getBannerPlaceholder() {
        List<ShimmerElement> shimmerElementList = new ArrayList<>();
        shimmerElementList.add(new ShimmerElement(Util.convertDpToPixels(10, context),
                Util.convertDpToPixels(16, context),
                Util.convertDpToPixels(80, context),
                Color.parseColor("#80ffffff"), ShimmerElement.BOTTOM));
        shimmerElementList.add(new ShimmerElement(Util.convertDpToPixels(14, context),
                Util.convertDpToPixels(16, context),
                Util.convertDpToPixels(30, context),
                Color.parseColor("#80ffffff"), ShimmerElement.BOTTOM));
        return new ShimmerDrawable(new int[]{ContextCompat.getColor(context, R.color.pinkish_grey)},
                new float[]{0},
                shimmerElementList)
                .setRoundedCorner(Util.convertDpToPixels(4, context))
                .setPaddingBottom(Util.convertDpToPixels(12, context))
                .setDividerPadding(Util.convertDpToPixels(5, context));
    }

    public ShimmerDrawable getTilePlaceHolder() {
        List<ShimmerElement> shimmerElementList = new ArrayList<>();
        shimmerElementList.add(new ShimmerElement(Util.convertDpToPixels(14, context),
                Util.convertDpToPixels(8, context),
                Util.convertDpToPixels(20, context),
                Color.parseColor("#80ffffff"), ShimmerElement.TOP));
        shimmerElementList.add(new ShimmerElement(Util.convertDpToPixels(10, context),
                Util.convertDpToPixels(8, context),
                Util.convertDpToPixels(60, context),
                Color.parseColor("#80ffffff"), ShimmerElement.TOP));
        return new ShimmerDrawable(new int[]{ContextCompat.getColor(context, R.color.pinkish_grey)},
                new float[]{0},
                shimmerElementList)
                .setRoundedCorner(Util.convertDpToPixels(4, context))
                .setPaddingTop(Util.convertDpToPixels(0, context))
                .setDividerPadding(Util.convertDpToPixels(5, context));
    }

    public ShimmerDrawable getTileRedPlaceholder() {
        List<ShimmerElement> shimmerElementList = new ArrayList<>();

        shimmerElementList.add(new ShimmerElement(Util.convertDpToPixels(10, context),
                Util.convertDpToPixels(60, context),
                Util.convertDpToPixels(8, context),
                Color.parseColor("#80ffffff"), ShimmerElement.TOP));

        shimmerElementList.add(new ShimmerElement(Util.convertDpToPixels(12, context),
                Util.convertDpToPixels(8, context),
                Util.convertDpToPixels(8, context),
                Color.parseColor("#80ffffff"), ShimmerElement.TOP));

        shimmerElementList.add(new ShimmerElement(Util.convertDpToPixels(10, context),
                Util.convertDpToPixels(8, context),
                Util.convertDpToPixels(60, context),
                Color.parseColor("#80ffffff"), ShimmerElement.TOP));

        shimmerElementList.add(new ShimmerElement(Util.convertDpToPixels(10, context),
                Util.convertDpToPixels(60, context),
                Util.convertDpToPixels(8, context),
                Color.parseColor("#66d0011b"), ShimmerElement.BOTTOM));

        return new ShimmerDrawable(new int[]{ContextCompat.getColor(context, R.color.scarlet), ContextCompat.getColor(context, R.color.white)},
                new float[]{0, 0.8f},
                shimmerElementList)
                .setRoundedCorner(Util.convertDpToPixels(4, context))
                .setPaddingTop(Util.convertDpToPixels(18, context))
                .setPaddingBottom(Util.convertDpToPixels(10, context))
                .setDividerPadding(Util.convertDpToPixels(5, context));
    }
}
