package com.ldw.coolweathermvvmjava.ui.weather;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ldw.coolweathermvvmjava.data.WeatherRepository;
import com.ldw.coolweathermvvmjava.data.model.weather.Weather;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by ldw
 */
public class WeatherViewModel extends ViewModel {
    private  WeatherRepository repository;

    public WeatherViewModel(WeatherRepository repository) {
        this.repository =repository;
    }
    public MutableLiveData<String> bingPicUrl = new MutableLiveData<>();
    public MutableLiveData<Boolean> refreshing = new MutableLiveData<>();
    public MutableLiveData<Weather> weather = new MutableLiveData<>();
    public MutableLiveData<Boolean> weatherInitialized = new MutableLiveData<>();

    public void onRefresh(){}

    @BindingAdapter("bind:loadBingPic")
    public static void loadBingPic(ImageView imageView,String picUrl){
        Glide.with(imageView.getContext()).load(picUrl).into(imageView);
    }
    @BindingAdapter("bind:colorSchemeResources")
    public static void colorSchemeResources(SwipeRefreshLayout refreshLayout,int resId){
        refreshLayout.setColorSchemeColors(resId);
    }

}
