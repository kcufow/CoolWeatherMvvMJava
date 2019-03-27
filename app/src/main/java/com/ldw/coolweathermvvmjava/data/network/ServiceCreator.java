package com.ldw.coolweathermvvmjava.data.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by ldw
 * time :2019/3/26.
 */
public class ServiceCreator {
    private static OkHttpClient.Builder builder;

    private ServiceCreator(){}


    private static final String  BASE_URL = "http://guolin.tech/";




    public static  <T> T create(Class<T> serviceClazz){
        OkHttpClient.Builder okHttpBuilder = getOkHttpBuilder();
        Retrofit.Builder builder = getRetrofitBuilder(okHttpBuilder);
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClazz);
    }

    private static OkHttpClient.Builder getOkHttpBuilder() {
        if (builder == null) {
            builder = new OkHttpClient.Builder();
        }

        return builder;
    }

    private static Retrofit.Builder getRetrofitBuilder(OkHttpClient.Builder okHttpBuilder) {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL).client(okHttpBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

}
