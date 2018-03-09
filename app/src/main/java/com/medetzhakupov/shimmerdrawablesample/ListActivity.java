package com.medetzhakupov.shimmerdrawablesample;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.medetzhakupov.shimmerdrawablesample.databinding.ActivityListBinding;
import com.medetzhakupov.shimmerdrawablesample.recyclerview.ItemAdapter;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        ShimmerHelper shimmerHelper = new ShimmerHelper(this);

        binding.list.setHasFixedSize(true);
        binding.list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        ItemAdapter<SimpleItemViewModel> adapter = new ItemAdapter<>();
        for (int i = 0; i < 50; i++) {
            adapter.addModel(new SimpleItemViewModel(shimmerHelper, i, R.layout.simple_item_list));
        }
        binding.list.setAdapter(adapter);
    }
}
