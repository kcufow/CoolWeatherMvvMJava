package com.ldw.coolweathermvvmjava.data.network;

import com.ldw.coolweathermvvmjava.data.model.place.City;
import com.ldw.coolweathermvvmjava.data.model.place.County;
import com.ldw.coolweathermvvmjava.data.model.place.Province;
import com.ldw.coolweathermvvmjava.data.model.weather.HeWeather;
import com.ldw.coolweathermvvmjava.data.network.api.PlaceService;
import com.ldw.coolweathermvvmjava.data.network.api.WeatherService;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by ldw
 */
public class CoolWeatherNetwork {

    public static CoolWeatherNetwork getInstance() {

        return CoolWeatherNetworkHolder.COOL_WEATHER_NETWORK;
    }


    private static class CoolWeatherNetworkHolder {
        static CoolWeatherNetwork COOL_WEATHER_NETWORK = new CoolWeatherNetwork();
    }

    private PlaceService placeService = ServiceCreator.create(PlaceService.class);
    private WeatherService weatherService = ServiceCreator.create(WeatherService.class);

    public void fetchProvinceData(Callback<List<Province>> callback) {
        Call<List<Province>> provinces = placeService.getProvinces();

        provinces.enqueue(callback);



    }

    public void fetchCityData(int provinceId, Callback<List<City>> callback) {
        Call<List<City>> cityListCall = placeService.getCities(provinceId);

        cityListCall.enqueue(callback);


    }

    public void fetchCountyData(int provinceId, int cityId, Callback<List<County>> callback) {
        Call<List<County>> countyListCall = placeService.getCounties(provinceId, cityId);

        countyListCall.enqueue(callback);


    }
    public void fetchWeatherData(String weatherId, String key,Callback<HeWeather> callback){
        Call<HeWeather> weatherCall = weatherService.getWeather(weatherId, key);
        weatherCall.enqueue(callback);

    }
    public void fetchBingPicData(Callback<String> callback){
        Call<String> bingPic = weatherService.getBingPic();
        bingPic.enqueue(callback);

    }

}
