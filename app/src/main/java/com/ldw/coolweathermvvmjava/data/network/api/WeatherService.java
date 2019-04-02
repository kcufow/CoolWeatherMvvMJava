package com.ldw.coolweathermvvmjava.data.network.api;

import com.ldw.coolweathermvvmjava.data.model.weather.HeWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherService {

    @GET("api/weather")
    Call<HeWeather> getWeather(@Query("cityid")String weatherId,@Query("key")String key);
    @GET("api/bing_pic")
    Call<String> getBingPic();
}
