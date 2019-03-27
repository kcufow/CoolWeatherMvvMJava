package com.ldw.coolweathermvvmjava.ui.area;

import android.view.View;
import android.widget.AdapterView;

import com.ldw.coolweathermvvmjava.data.network.PlaceRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by ldw
 */
public class ChooseAreaViewModel extends ViewModel {


    private PlaceRepository repository;

    public ChooseAreaViewModel(PlaceRepository repository) {
        this.repository = repository;
    }

    private MutableLiveData<Integer> currentLevel = new MutableLiveData<>();
    private MutableLiveData<Boolean> dataLoading = new MutableLiveData<>();

    public List<String> dataList = new ArrayList<>();


    public void onBack() {

    }

    public void onListViewItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
