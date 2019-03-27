package com.ldw.coolweathermvvmjava.data;


import com.ldw.coolweathermvvmjava.data.db.WeatherDao;
import com.ldw.coolweathermvvmjava.data.model.weather.Weather;
import com.ldw.coolweathermvvmjava.data.network.CoolWeatherNetwork;

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
}
