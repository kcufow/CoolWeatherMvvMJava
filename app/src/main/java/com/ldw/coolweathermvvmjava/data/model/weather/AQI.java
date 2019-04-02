package com.ldw.coolweathermvvmjava.data.model.weather;


import com.google.gson.annotations.SerializedName;

public class AQI {
    @SerializedName("city")
    public CityAQI cityAQI;

    public static class CityAQI{
        public String aqi;
        public String pm25;
        public String qlty;

    }
}
