package com.ldw.coolweathermvvmjava.data;


import android.text.TextUtils;

import com.ldw.coolweathermvvmjava.data.db.WeatherDao;
import com.ldw.coolweathermvvmjava.data.model.weather.HeWeather;
import com.ldw.coolweathermvvmjava.data.model.weather.Weather;
import com.ldw.coolweathermvvmjava.data.network.CoolWeatherNetwork;
import com.ldw.coolweathermvvmjava.data.network.DataFetchCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ldw
 */
public class WeatherRepository {

    private static WeatherRepository repository;



    private WeatherDao weatherDao;
    private CoolWeatherNetwork coolWeatherNetwork;
    private WeatherRepository(WeatherDao weatherDao, CoolWeatherNetwork coolWeatherNetwork) {
        this.weatherDao = weatherDao;
        this.coolWeatherNetwork = coolWeatherNetwork;
    }

    public static WeatherRepository getInstance(WeatherDao weatherDao, CoolWeatherNetwork coolWeatherNetwork) {
        if (repository==null){
            synchronized (WeatherRepository.class){
                if (repository == null) {
                    repository = new WeatherRepository(weatherDao,coolWeatherNetwork);
                }
            }
        }

        return repository;
    }

    public boolean isWeatherCached() {
        Weather cacheWeatherInfo = weatherDao.getCacheWeatherInfo();
        return cacheWeatherInfo!=null;
    }

    public Weather getWeatherCache() {
        return weatherDao.getCacheWeatherInfo();
    }

    public void getWeatherInfo(final String weatherId, String weatherKey, final DataFetchCallback<Weather> callback) {
        if (callback == null) return;
            coolWeatherNetwork.fetchWeatherData(weatherId, weatherKey, new Callback<HeWeather>() {
                @Override
                public void onResponse(Call<HeWeather> call, Response<HeWeather> response) {
                    HeWeather heWeather = response.body();
                    if (heWeather==null || heWeather.weathers== null || heWeather.weathers.size()==0){
                        callback.onFailed();
                        return;
                    }
                    weatherDao.cacheWeatherInfo(heWeather.weathers.get(0));
                    callback.onSuccess(heWeather.weathers);
                }

                @Override
                public void onFailure(Call<HeWeather> call, Throwable t) {
                    callback.onFailed();
                }
            });
    }

    public void getBingPic(final DataFetchCallback<String> callback) {
        coolWeatherNetwork.fetchBingPicData(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String picUrl = response.body();
                if (TextUtils.isEmpty(picUrl)) {
                    onBingPicFailure(callback);
                    return;
                }
                weatherDao.cacheBingPic(picUrl);
                List<String> list = new ArrayList<>();
                list.add(picUrl);
                callback.onSuccess(list);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                onBingPicFailure(callback);
            }
        });
    }

    private void onBingPicFailure(DataFetchCallback<String> callback) {
        String bingPic = weatherDao.getBingPic();
        if (TextUtils.isEmpty(bingPic)) {
            callback.onFailed();
            return;
        }
        List<String> list = new ArrayList<>();
        list.add(bingPic);
        callback.onSuccess(list);
    }
}
