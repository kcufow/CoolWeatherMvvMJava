package com.ldw.coolweathermvvmjava.data.network;

import com.ldw.coolweathermvvmjava.data.db.PlaceDao;

/**
 * Created by ldw
 */
public class PlaceRepository {
    private static PlaceRepository repository;
    private  PlaceDao placeDao;
    private  CoolWeatherNetwork coolWwatherNetWork;

    public PlaceRepository(PlaceDao placeDao, CoolWeatherNetwork coolWeatherNetwork) {
        this.placeDao=placeDao;
        this.coolWwatherNetWork = coolWeatherNetwork;
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
}
