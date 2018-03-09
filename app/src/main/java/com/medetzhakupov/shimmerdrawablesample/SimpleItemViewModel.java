package com.medetzhakupov.shimmerdrawablesample;

import android.annotation.SuppressLint;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.medetzhakupov.shimmerdrawable.ShimmerDrawable;
import com.medetzhakupov.shimmerdrawablesample.databinding.ItemListBinding;
import com.medetzhakupov.shimmerdrawablesample.databinding.SimpleItemListBinding;
import com.medetzhakupov.shimmerdrawablesample.recyclerview.BaseItemModel;
import com.medetzhakupov.shimmerdrawablesample.recyclerview.ItemAdapter;
import com.medetzhakupov.shimmerdrawablesample.recyclerview.ItemHolder;

import java.util.List;

/**
 * Created by MEDETZ on 12/18/2017.
 */

public class SimpleItemViewModel extends BaseItemModel<SimpleItemListBinding, SimpleItemViewModel.SimpleItemViewHolder> {

    private final int position;
    private final ShimmerHelper shimmerHelper;

    @LayoutRes
    private final int layoutId;

    public SimpleItemViewModel(final ShimmerHelper shimmerHelper, final int position, final int layoutId) {
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
    public SimpleItemViewHolder createHolder(final SimpleItemListBinding binding) {
        return new SimpleItemViewHolder(binding);
    }

    @Override
    public String getKey() {
        return position + "";
    }

    static class SimpleItemViewHolder extends ItemHolder<SimpleItemViewModel, SimpleItemListBinding> {

        SimpleItemViewHolder(final SimpleItemListBinding binding) {
            super(binding);
        }

        @Override
        protected void bind(SimpleItemViewModel model) {
            super.bind(model);
            binding.getRoot().setBackground(model.shimmerHelper.getBannerPlaceholder());
        }

        @Override
        protected void bind(SimpleItemViewModel model, List<Object> payloads) {
            if (payloads.contains(ItemAdapter.ITEMS_UPDATED)) {
                binding.getRoot().setBackground(null);
            }
        }

        @Override
        protected void attached() {
            super.attached();
            ((ShimmerDrawable) binding.getRoot().getBackground()).start();
        }

        @Override
        protected void detached() {
            super.detached();
            ((ShimmerDrawable) binding.getRoot().getBackground()).stop();
        }
    }

}
