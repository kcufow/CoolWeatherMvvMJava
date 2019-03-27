package com.ldw.coolweathermvvmjava.data.model.place;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;


public class Province extends LitePalSupport {
    @SerializedName("id")
    public int provinceCode;

    @SerializedName("name")
    public String provinceName;
}
