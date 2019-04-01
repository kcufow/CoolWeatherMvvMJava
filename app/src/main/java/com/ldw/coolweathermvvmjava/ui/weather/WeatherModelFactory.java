package com.ldw.coolweathermvvmjava.ui.weather;

import com.ldw.coolweathermvvmjava.data.WeatherRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by ldw
 */
public class WeatherModelFactory extends ViewModelProvider.NewInstanceFactory {
    private WeatherRepository repository;

    public WeatherModelFactory(WeatherRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WeatherViewModel(repository);
    }
}
