package com.ldw.coolweathermvvmjava.data.model.weather;

import com.google.gson.annotations.SerializedName;


public class Forecast {

    public String date;
    @SerializedName("tmp")
    public Temperature temperature;
    @SerializedName("cond")
    public More more;

    public static class Temperature {
        public String max;
        public String min;
    }
    public static class More{
        @SerializedName("txt_d")
        public String info;
    }
}
