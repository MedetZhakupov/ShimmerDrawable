package com.medetzhakupov.shimmerdrawablesample.recyclerview;

import android.databinding.ViewDataBinding;

import java.util.List;

/***
 * View holder for binding an ItemModel to a ViewDataBinding object
 * @param <M> The model to bind
 * @param <B> The databinding view object to bind the model to
 */
public abstract class ItemHolder<M extends ItemModel, B extends ViewDataBinding> {

    protected final B binding;

    public ItemHolder(final B binding) {
        this.binding = binding;
    }

    protected B getBinding() {
        return binding;
    }

    protected void bind(final M model) {
    }

    protected void bind(final M model, List<Object> payloads) {
    }

    protected void unbind() {
    }

    protected void attached() {
    }

    protected void detached() {
    }

}