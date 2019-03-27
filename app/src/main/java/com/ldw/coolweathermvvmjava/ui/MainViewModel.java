package com.ldw.coolweathermvvmjava.ui;

import com.ldw.coolweathermvvmjava.data.WeatherRepository;

import androidx.lifecycle.ViewModel;

/**
 * Created by ldw
 */
public class MainViewModel extends ViewModel {
    private WeatherRepository repository;

    public MainViewModel(WeatherRepository repository) {
        this.repository = repository;
    }

    public boolean isWeatherCached(){
        return repository.isWeatherCached();
    }

}
