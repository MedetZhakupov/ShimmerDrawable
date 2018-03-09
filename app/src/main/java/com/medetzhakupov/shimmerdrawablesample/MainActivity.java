package com.medetzhakupov.shimmerdrawablesample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SnapHelper;
import android.view.Gravity;
import android.view.View;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.medetzhakupov.shimmerdrawable.ShimmerDrawable;
import com.medetzhakupov.shimmerdrawablesample.databinding.ActivityMainBinding;
import com.medetzhakupov.shimmerdrawablesample.recyclerview.ItemAdapter;

public class MainActivity extends AppCompatActivity {

    ShimmerDrawable bannerOne, bannerTwo, bannerThree, bannerFour;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ShimmerHelper shimmerHelper = new ShimmerHelper(this);

        binding.list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        snapHelperStart.attachToRecyclerView(binding.list);
        ItemAdapter<ItemViewModel> adapter = new ItemAdapter<>();
        for (int i = 0; i < 3; i++) {
            adapter.addModel(new ItemViewModel(shimmerHelper, i, R.layout.item_list));
        }
        binding.list.setAdapter(adapter);
        binding.ivBannerOne.setBackground(bannerOne = shimmerHelper.getBannerPlaceholder());
        binding.ivBannerTwo.setBackground(bannerTwo = shimmerHelper.getBannerPlaceholder());
        binding.layoutCreditBalance.setBackground(bannerThree = shimmerHelper.getBannerPlaceholder());
        binding.layoutDataBalance.setBackground(bannerFour = shimmerHelper.getBannerPlaceholder());
        binding.ivBannerOne.setOnClickListener(v -> {
            startActivity(new Intent(this, ListActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bannerOne.start();
        bannerTwo.start();
        bannerThree.start();
        bannerFour.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bannerOne.start();
        bannerTwo.stop();
        bannerThree.start();
        bannerFour.start();
    }
}
