package com.ldw.coolweathermvvmjava.data.model.weather;


public class AQI {
    public CityAQI cityAQI;

    private static class CityAQI{
        public String aqi;
        public String pm25;
        public String qlty;

    }
}
