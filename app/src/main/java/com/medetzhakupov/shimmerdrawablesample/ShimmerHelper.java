package com.medetzhakupov.shimmerdrawablesample;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.medetzhakupov.shimmerdrawable.Shimmer;
import com.medetzhakupov.shimmerdrawable.ShimmerDrawable;
import com.medetzhakupov.shimmerdrawable.ShimmerElement;
import com.medetzhakupov.shimmerdrawable.ShimmerRect;

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
        List<Shimmer> shimmerElementList = new ArrayList<>();
        ShimmerRect s = new ShimmerRect(Util.convertDpToPixels(10, context),
                Color.parseColor("#80ffffff"), ShimmerElement.BOTTOM);
        s.setPaddingLeft(Util.convertDpToPixels(16, context));
        s.setPaddingRight(Util.convertDpToPixels(80, context));
        shimmerElementList.add(s);

        s = new ShimmerRect(Util.convertDpToPixels(14, context), Color.parseColor("#80ffffff"), ShimmerElement.BOTTOM);
        s.setPaddingLeft(Util.convertDpToPixels(16, context));
        s.setPaddingRight(Util.convertDpToPixels(30, context));
        shimmerElementList.add(s);
        return new ShimmerDrawable(new int[]{ContextCompat.getColor(context, R.color.pinkish_grey)},
                new float[]{0},
                shimmerElementList)
                .setRoundedCorner(Util.convertDpToPixels(4, context))
                .setPaddingBottom(Util.convertDpToPixels(12, context))
                .setDividerPadding(Util.convertDpToPixels(5, context));
    }

    public ShimmerDrawable getTilePlaceHolder() {
        List<Shimmer> shimmerElementList = new ArrayList<>();
        ShimmerRect s = new ShimmerRect(Util.convertDpToPixels(14, context),
                Color.parseColor("#80ffffff"), ShimmerElement.TOP);
        s.setPaddingLeft(Util.convertDpToPixels(8, context));
        s.setPaddingRight(Util.convertDpToPixels(20, context));
        shimmerElementList.add(s);

        s = new ShimmerRect(Util.convertDpToPixels(10, context),
                Color.parseColor("#80ffffff"), ShimmerElement.TOP);
        s.setPaddingLeft(Util.convertDpToPixels(8, context));
        s.setPaddingRight(Util.convertDpToPixels(60, context));
        shimmerElementList.add(s);

        return new ShimmerDrawable(new int[]{ContextCompat.getColor(context, R.color.pinkish_grey)},
                new float[]{0},
                shimmerElementList)
                .setRoundedCorner(Util.convertDpToPixels(4, context))
                .setPaddingTop(Util.convertDpToPixels(0, context))
                .setDividerPadding(Util.convertDpToPixels(5, context));
    }

    public ShimmerDrawable getTileRedPlaceholder() {
        List<Shimmer> shimmerElementList = new ArrayList<>();

        ShimmerRect s = new ShimmerRect(Util.convertDpToPixels(10, context),
                Color.parseColor("#80ffffff"), ShimmerElement.TOP);
        s.setPaddingLeft(Util.convertDpToPixels(60, context));
        s.setPaddingRight(Util.convertDpToPixels(8, context));
        shimmerElementList.add(s);

        s = new ShimmerRect(Util.convertDpToPixels(12, context),
                Color.parseColor("#80ffffff"), ShimmerElement.TOP);
        s.setPaddingLeft(Util.convertDpToPixels(8, context));
        s.setPaddingRight(Util.convertDpToPixels(8, context));
        shimmerElementList.add(s);

        s = new ShimmerRect(Util.convertDpToPixels(10, context),
                Color.parseColor("#80ffffff"), ShimmerElement.TOP);
        s.setPaddingLeft(Util.convertDpToPixels(8, context));
        s.setPaddingRight(Util.convertDpToPixels(60, context));
        shimmerElementList.add(s);

        s = new ShimmerRect(Util.convertDpToPixels(10, context),
                Color.parseColor("#66d0011b"), ShimmerElement.BOTTOM);
        s.setPaddingLeft(Util.convertDpToPixels(60, context));
        s.setPaddingRight(Util.convertDpToPixels(8, context));
        shimmerElementList.add(s);

        return new ShimmerDrawable(new int[]{ContextCompat.getColor(context, R.color.scarlet), ContextCompat.getColor(context, R.color.white)},
                new float[]{0, 0.8f},
                shimmerElementList)
                .setRoundedCorner(Util.convertDpToPixels(4, context))
                .setPaddingTop(Util.convertDpToPixels(18, context))
                .setPaddingBottom(Util.convertDpToPixels(10, context))
                .setDividerPadding(Util.convertDpToPixels(5, context));
    }
}
