package com.ldw.coolweathermvvmjava.data.model.weather;

import com.google.gson.annotations.SerializedName;


public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;

    public String getTemperature(){
        if (!temperature.isEmpty()) {
            return temperature+"℃";
        }
        return null;
    }

    public static class More{
        @SerializedName("txt")
        public String info;
    }
}
