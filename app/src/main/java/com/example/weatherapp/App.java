package com.example.weatherapp;

import android.app.Application;

import com.example.weatherapp.data.remote.RetrofitClient;
import com.example.weatherapp.data.remote.WeatherApi;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {
    public static WeatherApi api;
    private RetrofitClient retrofitClient;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.provideApi();
    }
}
