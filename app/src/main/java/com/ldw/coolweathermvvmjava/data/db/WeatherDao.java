package com.ldw.coolweathermvvmjava.data.db;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.ldw.coolweathermvvmjava.app.WeatherApp;
import com.ldw.coolweathermvvmjava.data.model.weather.Weather;

/**
 * Created by ldw
 */
public class WeatherDao {
    private static final String WEATHER_INFO = "weather_info";
    private static final String BING_PIC = "bing_pic";
    public void cacheWeatherInfo(Weather weather){
        if (weather == null) return;

        String s = new Gson().toJson(weather);
        cacheStringInfo(WEATHER_INFO,s);
    }
    public void cacheBingPic(String bingPic){
        if (TextUtils.isEmpty(bingPic)) return;

        cacheStringInfo(BING_PIC,bingPic);
    }

    public Weather getCacheWeatherInfo(){
        String weatherInfo = getDefaultSharedPreferences().getString(WEATHER_INFO, null);
        if (TextUtils.isEmpty(weatherInfo)) {
            return null;
        }

        return new Gson().fromJson(weatherInfo,Weather.class);
    }
    public String getBingPic(){
        String bingPic = getDefaultSharedPreferences().getString(BING_PIC, null);
        if (TextUtils.isEmpty(bingPic)) {
            return null;
        }

        return bingPic;
    }


    private SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(WeatherApp.getContext());
    }

    private void cacheStringInfo(String weatherInfo, String s) {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(WeatherApp.getContext()).edit();
        edit.putString(weatherInfo,s);
        edit.apply();
    }
}
