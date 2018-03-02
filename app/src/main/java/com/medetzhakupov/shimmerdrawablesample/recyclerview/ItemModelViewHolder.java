package com.medetzhakupov.shimmerdrawablesample.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

import java.util.List;
/***
 * Internal RecyclerView.ViewHolder used only by the ItemAdapter to bind an
 * ItemModel to an ItemHolder
 */
class ItemModelViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private final B binding;
    private ItemHolder holder;

    ItemModelViewHolder(final B binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(ItemModel viewModel) {
        if (holder == null) {
            holder = viewModel.createHolder(binding);
        }
        holder.bind(viewModel);
    }

    void bind(ItemModel viewModel, List<Object> payloads) {
        if (holder == null) {
            bind(viewModel);
            return;
        }
        holder.bind(viewModel, payloads);
    }

    void unbind() {
        if (holder != null) {
            holder.unbind();
        }
    }

    void onAttachedToWindow() {
        if (holder != null) {
            holder.attached();
        }
    }

    void onDetachedFromWindow() {
        if (holder != null) {
            holder.detached();
        }
    }
}