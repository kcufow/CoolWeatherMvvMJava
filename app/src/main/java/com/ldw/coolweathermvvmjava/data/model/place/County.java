package com.ldw.coolweathermvvmjava.data.model.place;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;


public class County extends LitePalSupport {
    @SerializedName("id")
    public int countyCode;
    public int cityId;

    @SerializedName("name")
    public String countyName;

    @SerializedName("weather_id")
    public String weatherId;



}
