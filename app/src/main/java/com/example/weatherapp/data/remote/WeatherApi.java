package com.example.weatherapp.data.remote;

import com.example.weatherapp.data.models.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {


    @GET("weather?")
    Call<Example> getCurrentForeCast(
            @Query("q") String city,
            @Query("appid") String appId,
            @Query("units") String metric
    );
}
