package com.ldw.coolweathermvvmjava.data.model.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class HeWeather {
    @SerializedName("HeWeather")
    public List<Weather> weathers;
}
