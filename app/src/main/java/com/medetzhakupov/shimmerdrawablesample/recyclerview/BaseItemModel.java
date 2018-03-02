package com.medetzhakupov.shimmerdrawablesample.recyclerview;

import android.databinding.ViewDataBinding;

/**
 * Base class for RecyclerView ItemModels to be used with the ItemAdapter
 */
public abstract class BaseItemModel<Binding extends ViewDataBinding, Holder extends ItemHolder> implements ItemModel<Binding, Holder> {

    Long id;

    public BaseItemModel() {
    }

    final void setId(Long id) {
        this.id = id;
    }

    @Override
    public final Long getId() {
        return id;
    }

    /**
     * Override to provide a unique key for the data defined by this object model. If the data is refreshed and a new model created
     * this key will allow RecyclerView to know that the 2 different models relate to the same data and animations will be correct.
     * Returning null from this method means each model instance will get a unique id  which will be appropriate if the data is never
     * refreshed.
     */
    @Override
    public abstract String getKey();

    /***
     * Override to support better diffing of contents of the model when updating the adapter items.
     * This is called from the DiffUtil.Callback.areItemsTheSame() method when updating ItemAdapter models using updateModels().
     */
    @Override
    public boolean isTheSameItem(final ItemModel itemModel) {
        return getLayoutId() == itemModel.getLayoutId();
    }

    /***
     * Override to support better diffing of contents of the model when updating the adapter items.
     * This is called from the DiffUtil.Callback.areContentsTheSame() method when updating ItemAdapter models using updateModels().
     */
    @Override
    public boolean hasTheSameContents(final ItemModel itemModel) {
        return false;
    }

}
