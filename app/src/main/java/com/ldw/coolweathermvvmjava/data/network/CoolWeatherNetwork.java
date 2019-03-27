package com.ldw.coolweathermvvmjava.data.network;

/**
 * Created by ldw
 */
public class CoolWeatherNetwork {

    public static CoolWeatherNetwork getInstance(){

        return CoolWeatherNetworkHolder.COOL_WEATHER_NETWORK;
    }


    private static class CoolWeatherNetworkHolder{
         static  CoolWeatherNetwork COOL_WEATHER_NETWORK = new CoolWeatherNetwork();
    }

}
