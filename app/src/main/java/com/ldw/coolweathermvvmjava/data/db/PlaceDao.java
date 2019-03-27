package com.ldw.coolweathermvvmjava.data.db;

import com.ldw.coolweathermvvmjava.data.model.place.City;
import com.ldw.coolweathermvvmjava.data.model.place.County;
import com.ldw.coolweathermvvmjava.data.model.place.Province;

import org.litepal.LitePal;
import java.util.List;

/**
 * Created by ldw
 */
public class PlaceDao {

    public List<Province> getProvinces() {

        return LitePal.findAll(Province.class);
    }

    public List<City> getCities(int provinceId) {
        return LitePal.where("provinceId = ?", String.valueOf(provinceId)).find(City.class);
    }

    public List<County> getCounties(int cityId) {
        return LitePal.where("cityId = ?", String.valueOf(cityId)).find(County.class);
    }

    public void saveProvinces(List<Province> provinces) {
        if (provinces != null && provinces.size() > 0) {

            LitePal.saveAll(provinces);
        }

    }

    public void saveCities(List<City> cities) {
        if (cities != null && cities.size() > 0) {

            LitePal.saveAll(cities);
        }

    }

    public void saveCounties(List<County> counties) {
        if (counties != null && counties.size() > 0) {

            LitePal.saveAll(counties);
        }

    }


}
