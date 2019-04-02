package com.ldw.coolweathermvvmjava.ui.weather;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.ldw.coolweathermvvmjava.R;
import com.ldw.coolweathermvvmjava.app.Constant;
import com.ldw.coolweathermvvmjava.data.WeatherRepository;
import com.ldw.coolweathermvvmjava.data.model.weather.Forecast;
import com.ldw.coolweathermvvmjava.data.model.weather.Weather;
import com.ldw.coolweathermvvmjava.data.network.DataFetchCallback;
import com.ldw.coolweathermvvmjava.databinding.ForecastItemBinding;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by ldw
 */
public class WeatherViewModel extends ViewModel {
    public String weatherKey= Constant.KEY;
    private  WeatherRepository repository;
    public String weatherId;

    public WeatherViewModel(WeatherRepository repository) {
        this.repository =repository;
    }
    public MutableLiveData<String> bingPicUrl = new MutableLiveData<>();
    public MutableLiveData<Boolean> refreshing = new MutableLiveData<>();
    public MutableLiveData<Boolean> fetchDataResult = new MutableLiveData<>();
    public MutableLiveData<Weather> weather = new MutableLiveData<>();
    public MutableLiveData<Boolean> weatherInitialized = new MutableLiveData<>();

    public void onRefresh(){
            refreshWeather();
    }

    @BindingAdapter("bind:loadBingPic")
    public static void loadBingPic(ImageView imageView,String picUrl){
        Glide.with(imageView.getContext()).load(picUrl).into(imageView);
    }
    @BindingAdapter("bind:colorSchemeResources")
    public static void colorSchemeResources(SwipeRefreshLayout refreshLayout,int resId){
        refreshLayout.setColorSchemeColors(resId);
    }
    @BindingAdapter("bind:showForecast")
    public static void showForecast(LinearLayout container, Weather  weather){
        container.removeAllViews();

        if (weather == null) {

            return;
        }

        for (Forecast dailyForecast : weather.dailyForecasts) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.forecast_item,container
                    ,false);
            ForecastItemBinding itemBinding = ForecastItemBinding.bind(view);
            itemBinding.setForecast(dailyForecast);
            container.addView(view);

        }

    }
    public boolean isWeatherCached(){

        return repository.isWeatherCached();
    }

    public Weather getWeatherCache(){

        return repository.getWeatherCache();
    }

    public void refreshWeather() {
            refreshing.setValue(true);

            repository.getWeatherInfo(weatherId,weatherKey,new DataFetchCallback<Weather>(){

                @Override
                public void onSuccess(List<Weather> datas) {
                    Weather value = datas.get(0);
                    weather.postValue(value);
                    refreshing.postValue(false);
                    fetchDataResult.postValue(true);
                }

                @Override
                public void onFailed() {
                    refreshing.postValue(false);
                    fetchDataResult.postValue(false);
                }
            });

    }

    public void getWeather() {
        refreshing.setValue(true);

        repository.getWeatherInfo(weatherId,weatherKey,new DataFetchCallback<Weather>(){

            @Override
            public void onSuccess(List<Weather> datas) {

                weatherInitialized.postValue(true);
                Weather value = datas.get(0);
                weather.postValue(value);
                refreshing.postValue(false);
                fetchDataResult.postValue(true);
            }

            @Override
            public void onFailed() {
                refreshing.postValue(false);
                fetchDataResult.postValue(false);
            }
        });

        repository.getBingPic(new DataFetchCallback<String>(){

            @Override
            public void onSuccess(List<String> datas) {
                String s = datas.get(0);
                bingPicUrl.postValue(s);
            }

            @Override
            public void onFailed() {
                Log.e("bingPic", "onFailed: ");
            }
        });
    }
}
