package com.medetzhakupov.shimmerdrawablesample;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.medetzhakupov.shimmerdrawable.ShimmerDrawable;
import com.medetzhakupov.shimmerdrawablesample.databinding.ItemListBinding;
import com.medetzhakupov.shimmerdrawablesample.recyclerview.BaseItemModel;
import com.medetzhakupov.shimmerdrawablesample.recyclerview.ItemHolder;

/**
 * Created by MEDETZ on 12/18/2017.
 */

public class ItemViewModel extends BaseItemModel<ItemListBinding, ItemViewModel.SimpleItemViewHolder> {

    private final int position;
    private final ShimmerHelper shimmerHelper;

    @LayoutRes
    private final int layoutId;

    public ItemViewModel(final ShimmerHelper shimmerHelper, final int position, final int layoutId) {
        this.position = position;
        this.layoutId = layoutId;
        this.shimmerHelper = shimmerHelper;
    }

    @Override
    public int getLayoutId() {
        return layoutId;
    }

    @SuppressLint("NewApi")
    @Override
    public SimpleItemViewHolder createHolder(final ItemListBinding binding) {
        final View rootView = binding.getRoot();
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            android.view.ViewGroup.LayoutParams mParams = rootView.getLayoutParams();
            mParams.width = rootView.getHeight();
            rootView.setLayoutParams(mParams);
        });
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)rootView.getLayoutParams();

        if (position == 1) {
            int left = - binding.getRoot().getContext().getResources().getDimensionPixelSize(R.dimen.margin_normal);
            params.setMargins(left, 0, 0, 0);
            rootView.setLayoutParams(params);
        } else {
            params.setMargins(0, 0, 0, 0);
        }

        rootView.setLayoutParams(params);

        if (position == 0) {
            binding.frameLayout.setBackground(shimmerHelper.getTilePlaceHolder());
        } else {
            binding.frameLayout.setBackground(shimmerHelper.getTileRedPlaceholder());
        }
        return new SimpleItemViewHolder(binding);
    }

    @Override
    public String getKey() {
        return position + "";
    }

    static class SimpleItemViewHolder extends ItemHolder<ItemViewModel, ItemListBinding> {

        SimpleItemViewHolder(final ItemListBinding binding) {
            super(binding);
        }

        @Override
        protected void attached() {
            super.attached();
            ((ShimmerDrawable)binding.frameLayout.getBackground()).start();
        }

        @Override
        protected void detached() {
            super.detached();
            ((ShimmerDrawable)binding.frameLayout.getBackground()).stop();
        }
    }

}
