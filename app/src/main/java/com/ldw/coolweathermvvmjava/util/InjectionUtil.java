package com.ldw.coolweathermvvmjava.util;

import com.ldw.coolweathermvvmjava.data.WeatherRepository;
import com.ldw.coolweathermvvmjava.data.db.CoolWeatherDatabase;
import com.ldw.coolweathermvvmjava.data.network.CoolWeatherNetwork;
import com.ldw.coolweathermvvmjava.data.network.PlaceRepository;
import com.ldw.coolweathermvvmjava.ui.MainViewModelFactory;
import com.ldw.coolweathermvvmjava.ui.area.ChooseAreaModelFactory;

/**
 * Created by ldw
 */
public class InjectionUtil {

    private static WeatherRepository getWeatherRepository() {
        return WeatherRepository.getInstance(CoolWeatherDatabase.getInstance().getWeatherDao()
                , CoolWeatherNetwork.getInstance());
    }
    private static PlaceRepository getPlaceRepository() {
        return PlaceRepository.getInstance(CoolWeatherDatabase.getInstance().getPlaceDao()
                , CoolWeatherNetwork.getInstance());
    }
    public static MainViewModelFactory getMainViewFactory(){

        return new MainViewModelFactory(getWeatherRepository());
    }
    public static ChooseAreaModelFactory getChooseAreaModelFactory(){

        return new ChooseAreaModelFactory(getPlaceRepository());
    }




}
