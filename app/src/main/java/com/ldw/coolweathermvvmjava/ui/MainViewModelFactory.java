package com.ldw.coolweathermvvmjava.ui;

import android.view.View;

import com.ldw.coolweathermvvmjava.data.WeatherRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by ldw
 */
public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private WeatherRepository repository;
    public MainViewModelFactory( WeatherRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {



        return (T) new MainViewModel(repository);
    }
}
