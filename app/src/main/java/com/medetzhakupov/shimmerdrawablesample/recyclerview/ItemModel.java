package com.medetzhakupov.shimmerdrawablesample.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;

/**
 * Interface for a generic RecyclerView item ViewModel that binds and unbinds a view binding.
 */
public interface ItemModel<Binding extends ViewDataBinding, Holder extends ItemHolder> {

    Long getId();

    String getKey();

    @LayoutRes
    int getLayoutId();

    Holder createHolder(Binding binding);

    boolean isTheSameItem(ItemModel itemModel);

    boolean hasTheSameContents(ItemModel itemModel);
}
