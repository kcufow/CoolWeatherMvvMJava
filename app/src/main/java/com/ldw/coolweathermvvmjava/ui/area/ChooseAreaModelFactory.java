package com.ldw.coolweathermvvmjava.ui.area;

import com.ldw.coolweathermvvmjava.data.network.PlaceRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * Created by ldw
 */
public class ChooseAreaModelFactory extends ViewModelProvider.NewInstanceFactory {
 private PlaceRepository repository;

    public ChooseAreaModelFactory(PlaceRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ChooseAreaViewModel(repository);
    }
}
