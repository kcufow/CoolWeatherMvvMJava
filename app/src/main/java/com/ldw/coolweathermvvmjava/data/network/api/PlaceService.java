package com.ldw.coolweathermvvmjava.data.network.api;


import com.ldw.coolweathermvvmjava.data.model.place.City;
import com.ldw.coolweathermvvmjava.data.model.place.County;
import com.ldw.coolweathermvvmjava.data.model.place.Province;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaceService {

  /*  @GET("api/china")
    fun getProvinces(): Call<MutableList<Province>>

    @GET("api/china/{provinceId}")
    fun getCities(@Path("provinceId") provinceId: Int): Call<MutableList<City>>

    @GET("api/china/{provinceId}/{cityId}")
    fun getCounties(@Path("provinceId") provinceId: Int, @Path("cityId") cityId: Int): Call<MutableList<County>>
*/
    @GET("/api/china")
  Call<List<Province>> getProvinces();
    @GET("/api/china/{provinceId}")
    Call<List<City>> getCities(@Path("provinceId")int provinceId);
    @GET("api/china/{provinceId}/{cityId}")
    Call<List<County>> getCounties(@Path("provinceId")int provinceId, @Path("cityId")int cityId);

}
