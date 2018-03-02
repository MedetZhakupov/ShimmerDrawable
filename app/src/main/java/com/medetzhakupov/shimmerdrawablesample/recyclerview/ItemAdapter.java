package com.medetzhakupov.shimmerdrawablesample.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.VisibleForTesting;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/***
 * Generic ItemModel adapter to be used with RecyclerView to display a set of models
 */
public class ItemAdapter<M extends ItemModel> extends RecyclerView.Adapter<ItemModelViewHolder> {

    public static final int ITEMS_UPDATED = 0;

    /***
     * Generated ids so we have static ids on recyclerview items.
     * Helps with animations on entry and exit & state management.
     * Using negative long to avoid conflict with potential user added id's
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    static final AtomicLong ID_COUNTER = new AtomicLong(0);

    private final List<M> models = new ArrayList<>(0);
    private final Map<Object, Long> ids = new HashMap<>();

    public ItemAdapter() {
        setHasStableIds(true);
    }

    public void addModel(M model) {
        int preAddCount = getItemCount();
        models.add(model);
        notifyItemRangeInserted(preAddCount, 1);
    }

    public void addModels(List<M> modelsToAdd) {
        int preAddCount = getItemCount();
        models.addAll(modelsToAdd);
        notifyItemRangeInserted(preAddCount, modelsToAdd.size());
    }

    public void updateModels(List<M> modelsToUpdate) {
        if (isEmpty()) {
            // no need to update
            addModels(modelsToUpdate);
        } else {
            if (modelsToUpdate == null) {
                modelsToUpdate = new ArrayList<>();
            }
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ItemDiffCallback(models, modelsToUpdate));
            models.clear();
            models.addAll(modelsToUpdate);
            diffResult.dispatchUpdatesTo(this);
        }
    }

    public void clear() {
        models.clear();
        notifyDataSetChanged();
    }

    public M getModel(int position) {
        return models.get(position);
    }

    @Override
    public ItemModelViewHolder onCreateViewHolder(final ViewGroup parent, final int layoutResId) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutResId, parent, false);
        return new ItemModelViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(ItemModelViewHolder holder, int position) {
        holder.bind(getModel(position));
    }

    @Override
    public void onBindViewHolder(ItemModelViewHolder holder, int position, List<Object> payloads) {
        if (payloads.contains(ITEMS_UPDATED)) {
            holder.bind(getModel(position), payloads);
        } else {
            super.onBindViewHolder(holder, position, payloads);
        }
    }

    @Override
    public int getItemViewType(final int position) {
        // using the layout id as a view type to support any number of layout types
        return getModel(position).getLayoutId();
    }

    @Override
    public long getItemId(final int position) {
        return getModelId(getModel(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public boolean isEmpty() {
        return models.isEmpty();
    }

    @Override
    public void onViewRecycled(ItemModelViewHolder holder) {
        super.onViewRecycled(holder);
        holder.unbind();
    }

    @Override
    public void onViewAttachedToWindow(ItemModelViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.onAttachedToWindow();
    }

    @Override
    public void onViewDetachedFromWindow(ItemModelViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onDetachedFromWindow();
    }

    private Long getModelId(M model) {
        Long id = model.getId();
        if (id != null) {
            return id;
        }

        String key = model.getKey();
        if (key != null) {
            id = ids.get(key);
        }

        if (id == null) {
            id = ID_COUNTER.decrementAndGet();
            if (model instanceof BaseItemModel) {
                ((BaseItemModel) model).setId(id);
            }
            if (key != null) {
                ids.put(key, id);
            }
        }
        return id;
    }

    /***
     * Diff util for comparing models on update. Offloads diff comparison to the ItemModel
     */
    private static final class ItemDiffCallback extends DiffUtil.Callback {

        private final List<? extends ItemModel> oldList;
        private final List<? extends ItemModel> newList;

        private ItemDiffCallback(List<? extends ItemModel> oldList, List<? extends ItemModel> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            ItemModel oldModel = oldList.get(oldItemPosition);
            ItemModel newModel = newList.get(newItemPosition);
            return oldModel == null && newModel == null ||
                    (oldModel != null && newModel != null && oldModel.isTheSameItem(newModel));
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            ItemModel oldModel = oldList.get(oldItemPosition);
            ItemModel newModel = newList.get(newItemPosition);
            return oldModel == null && newModel == null ||
                    (oldModel != null && newModel != null && oldModel.hasTheSameContents(newModel));
        }

        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return ITEMS_UPDATED;
        }
    }
}
