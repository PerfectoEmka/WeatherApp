package com.example.weatherapp.di;

import com.example.weatherapp.data.remote.RetrofitClient;
import com.example.weatherapp.data.remote.WeatherApi;
import com.example.weatherapp.data.repositories.MainRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static WeatherApi provideApi(){
        return new RetrofitClient().provideApi();
    }

    @Provides
    public static MainRepository provideRepository(WeatherApi api){
        return new MainRepository(api);
    }

}
