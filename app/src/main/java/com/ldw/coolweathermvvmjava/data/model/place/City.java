package com.ldw.coolweathermvvmjava.data.model.place;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;


public class City extends LitePalSupport {
    @SerializedName("id")
    public int cityCode;
    public int provinceId;
    @SerializedName("name")
    public String cityName;
}
