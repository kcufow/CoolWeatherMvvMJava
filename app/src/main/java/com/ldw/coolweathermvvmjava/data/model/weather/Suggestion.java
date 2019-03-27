package com.ldw.coolweathermvvmjava.data.model.weather;

import com.google.gson.annotations.SerializedName;


public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;
    @SerializedName("cw")
    public CarWash carWash;
    public Sport sport;

    private static class Comfort{
        @SerializedName("txt")
        public String info;
    }
    private static class CarWash{
        @SerializedName("txt")
        public String info;
    }
    private static class Sport{
        @SerializedName("txt")
        public String info;
    }
}
