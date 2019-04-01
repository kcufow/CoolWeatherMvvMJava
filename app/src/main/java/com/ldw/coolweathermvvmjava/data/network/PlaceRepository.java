package com.ldw.coolweathermvvmjava.data.network;

import com.ldw.coolweathermvvmjava.data.db.PlaceDao;
import com.ldw.coolweathermvvmjava.data.model.place.City;
import com.ldw.coolweathermvvmjava.data.model.place.County;
import com.ldw.coolweathermvvmjava.data.model.place.Province;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ldw
 */
public class PlaceRepository {
    private static PlaceRepository repository;
    private  PlaceDao placeDao;
    private  CoolWeatherNetwork coolWeatherNetWork;

    public PlaceRepository(PlaceDao placeDao, CoolWeatherNetwork coolWeatherNetwork) {
        this.placeDao=placeDao;
        this.coolWeatherNetWork = coolWeatherNetwork;
    }

    public static PlaceRepository getInstance(PlaceDao placeDao, CoolWeatherNetwork coolWeatherNetwork) {
        if (repository==null){
            synchronized (PlaceRepository.class){
                if (repository == null) {
                    repository = new PlaceRepository(placeDao,coolWeatherNetwork);
                }
            }
        }

        return repository;
    }


    public void getProvinceData(final DataFetchCallback<Province> callback){
        if (callback == null)return;
        List<Province> provinces = placeDao.getProvinces();
        if (!provinces.isEmpty()){
          callback.onSuccess(provinces);
          return;
        }
        coolWeatherNetWork.fetchProvinceData(new Callback<List<Province>>() {
            @Override
            public void onResponse(Call<List<Province>> call, Response<List<Province>> response) {
                List<Province> provinceList = response.body();
                if (provinceList == null || provinceList.isEmpty()) {
                    callback.onFailed();
                    return;
                }
                placeDao.saveProvinces(provinceList);
                callback.onSuccess(provinceList);
            }

            @Override
            public void onFailure(Call<List<Province>> call, Throwable t) {
                callback.onFailed();
            }
        });

    }
    public void getCityData(int provinceId,final DataFetchCallback<City> callback){
        if (callback==null)return;
        List<City> cities = placeDao.getCities(provinceId);
        if (!cities.isEmpty()) {
            callback.onSuccess(cities);
            return;
        }
       coolWeatherNetWork.fetchCityData(provinceId, new Callback<List<City>>() {
           @Override
           public void onResponse(Call<List<City>> call, Response<List<City>> response) {
               List<City> cityList = response.body();
               if (cityList==null || cityList.isEmpty()){
                   callback.onFailed();
                   return;
               }
               placeDao.saveCities(cityList);
               callback.onSuccess(cityList);
           }

           @Override
           public void onFailure(Call<List<City>> call, Throwable t) {
               callback.onFailed();
           }
       });


    }

    public void getCountyData(int provinceId,int cityId,final DataFetchCallback<County> callback) {
        if (callback == null)return;
        List<County> counties = placeDao.getCounties(cityId);
        if (!counties.isEmpty()) {
            callback.onSuccess(counties);
            return;
        }
        coolWeatherNetWork.fetchCountyData(provinceId, cityId, new Callback<List<County>>() {
            @Override
            public void onResponse(Call<List<County>> call, Response<List<County>> response) {
                List<County> countyList = response.body();
                if (countyList==null ||  countyList.isEmpty()){
                    callback.onFailed();
                    return;
                }
                placeDao.saveCounties(countyList);
                callback.onSuccess(countyList);
            }

            @Override
            public void onFailure(Call<List<County>> call, Throwable t) {
                    callback.onFailed();
            }
        });

    }
}
