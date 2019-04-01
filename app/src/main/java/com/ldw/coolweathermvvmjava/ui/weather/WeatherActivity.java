package com.ldw.coolweathermvvmjava.ui.weather;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.ldw.coolweathermvvmjava.R;
import com.ldw.coolweathermvvmjava.databinding.ActivityWeatherBinding;
import com.ldw.coolweathermvvmjava.util.InjectionUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
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
        dataBinding.setLifecycleOwner(this);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        dataBinding.setViewModel(obtainViewModel());
        dataBinding.setResId(R.color.colorPrimary);


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

    }
}
