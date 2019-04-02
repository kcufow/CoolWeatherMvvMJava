package com.ldw.coolweathermvvmjava.ui.weather;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.ldw.coolweathermvvmjava.R;
import com.ldw.coolweathermvvmjava.app.Constant;
import com.ldw.coolweathermvvmjava.databinding.ActivityWeatherBinding;
import com.ldw.coolweathermvvmjava.util.InjectionUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class WeatherActivity extends AppCompatActivity {

    private ActivityWeatherBinding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor( Color.TRANSPARENT);
        }

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        dataBinding.setLifecycleOwner(this);
        dataBinding.setViewModel(obtainViewModel());
        dataBinding.setResId(R.color.colorPrimary);

        observe();
        WeatherViewModel viewModel = dataBinding.getViewModel();
        if (viewModel.isWeatherCached()){
            viewModel.weatherId = viewModel.getWeatherCache().basic.weatherId;
        }else {

            Intent intent = getIntent();
            String weatherId = intent.getStringExtra(Constant.WEATHER_ID);
            if (TextUtils.isEmpty(weatherId)) {
                Toast.makeText(this, "数据有误请重新打开", Toast.LENGTH_SHORT).show();
                finish();
            }
            viewModel.weatherId = weatherId;
        }

            viewModel.getWeather();

    }

    private void observe() {
        dataBinding.getViewModel().fetchDataResult.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (!aBoolean){
                    Toast.makeText(WeatherActivity.this,"数据获取失败",Toast.LENGTH_SHORT).show();
                }
            }
        });


        dataBinding.getRoot().findViewById(R.id.navButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBinding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }


    private WeatherViewModel obtainViewModel() {

        return ViewModelProviders.of(this,InjectionUtil.getWeatherModelFactory())
                .get(WeatherViewModel.class);
    }

    /**
     * 更新天气信息
     * @param key key
     * @param weatherId weatherId
     * @param b 是否closeDrawer，true close
     */
    public void refreshWeather(String key, String weatherId, boolean b) {
        if (b){
            dataBinding.drawerLayout.closeDrawers();
        }
        WeatherViewModel viewModel = dataBinding.getViewModel();
        viewModel.weatherId = weatherId;
        viewModel.weatherKey = key;
        viewModel.refreshWeather();

    }
}
