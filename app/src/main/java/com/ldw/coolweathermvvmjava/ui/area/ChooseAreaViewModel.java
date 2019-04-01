package com.ldw.coolweathermvvmjava.ui.area;

import android.view.View;
import android.widget.AdapterView;

import com.ldw.coolweathermvvmjava.data.model.place.City;
import com.ldw.coolweathermvvmjava.data.model.place.County;
import com.ldw.coolweathermvvmjava.data.model.place.Province;
import com.ldw.coolweathermvvmjava.data.network.DataFetchCallback;
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

    public MutableLiveData<Integer> currentLevel = new MutableLiveData<>();
    public MutableLiveData<Boolean> dataLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> areaSelected = new MutableLiveData<>();
    public MutableLiveData<Boolean> dataSetChange = new MutableLiveData<>();
    public MutableLiveData<Integer> selectedPosition = new MutableLiveData<>();


    public Province selectedProvince;
    public City selectedCity;
    public County selectedCounty;

    public List<String> dataList = new ArrayList<>();
    public List<Province> provinces ;
    public List<City> cities ;
    public List<County> counties ;


    public void onBack() {
        int level = currentLevel.getValue();
        if (ChooseAreaFragment.LEVEL_COUNTY ==level ){
            getCityData(selectedProvince.provinceCode);


        }else if (ChooseAreaFragment.LEVEL_CITY == level){
            getProvinceData();
        }

    }

    public void onListViewItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Log.e("onItemClick==>", "onListViewItemClick: "+id +" position ："+position);
        switch (currentLevel.getValue()){
            case ChooseAreaFragment.LEVEL_PROVINCE:
                selectedProvince = provinces.get(position);
                getCityData(selectedProvince.provinceCode);
                break;
            case ChooseAreaFragment.LEVEL_CITY:
                selectedCity = cities.get(position);
                getCountyData(selectedProvince.provinceCode,selectedCity.cityCode);
                break;
            case ChooseAreaFragment.LEVEL_COUNTY:
                selectedCounty = counties.get(position);
                areaSelected.setValue(true);
                break;
        }


    }

    public void getProvinceData() {
        currentLevel.setValue(ChooseAreaFragment.LEVEL_PROVINCE);
        dataLoading.setValue(true);
        dataList.clear();
        repository.getProvinceData(new DataFetchCallback<Province>(){

            @Override
            public void onSuccess(List<Province> datas) {
                provinces = datas;
                for (Province province : provinces) {
                    dataList.add(province.provinceName);
                }
                dataSetChange.postValue(true);

                dataLoading.postValue(false);
            }

            @Override
            public void onFailed() {
               dataSetChange.postValue(false);
            dataLoading.postValue(false);
            }
        });



    }

    private void getCityData(int provinceId){
        currentLevel.setValue(ChooseAreaFragment.LEVEL_CITY);
        dataLoading.setValue(true);
        dataList.clear();
       repository.getCityData(provinceId, new DataFetchCallback<City>() {
            @Override
            public void onSuccess(List<City> datas) {
                cities = datas;
                for (City city : cities) {
                    dataList.add(city.cityName);
                }
                dataSetChange.postValue(true);
                dataLoading.postValue(false);
            }

            @Override
            public void onFailed() {
                dataSetChange.postValue(false);
                dataLoading.postValue(false);
            }
        });

    }
    private void getCountyData(int provinceId,int cityId){
        currentLevel.setValue(ChooseAreaFragment.LEVEL_COUNTY);
        dataLoading.setValue(true);
        dataList.clear();
         repository.getCountyData(provinceId, cityId, new DataFetchCallback<County>() {
             @Override
             public void onSuccess(List<County> datas) {
                 counties =datas;
                 for (County county : counties) {
                     dataList.add(county.countyName);
                 }
                 dataSetChange.postValue(true);
                 dataLoading.postValue(false);
             }

             @Override
             public void onFailed() {
                 dataSetChange.postValue(false);
                 dataLoading.postValue(false);
             }
         });

    }
}
