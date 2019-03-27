package com.ldw.coolweathermvvmjava.data.model.weather;

import com.google.gson.annotations.SerializedName;


public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;

    private static class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
