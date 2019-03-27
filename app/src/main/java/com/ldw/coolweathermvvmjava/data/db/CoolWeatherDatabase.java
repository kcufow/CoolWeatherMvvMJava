package com.ldw.coolweathermvvmjava.data.db;

/**
 * Created by ldw
 */
public class CoolWeatherDatabase {
    private CoolWeatherDatabase(){}
    public static CoolWeatherDatabase getInstance(){
        return CoolWeatherDatabaseHolder.coolWeatherDatabase;
    }
    private static class CoolWeatherDatabaseHolder{
        static CoolWeatherDatabase coolWeatherDatabase = new CoolWeatherDatabase();
    }
    private WeatherDao weatherDao;
    private PlaceDao placeDao;

    public WeatherDao getWeatherDao(){
        if (weatherDao == null) {
            weatherDao = new WeatherDao();
        }
        return weatherDao;
    }
    public PlaceDao getPlaceDao(){
        if (placeDao == null) {
            placeDao = new PlaceDao();
        }

        return placeDao;
    }
}
