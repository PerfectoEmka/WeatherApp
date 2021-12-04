package com.example.weatherapp.data.repositories;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.local.WeatherDao;
import com.example.weatherapp.data.models.Example;
import com.example.weatherapp.data.models.daily.forecast;
import com.example.weatherapp.data.remote.WeatherApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private WeatherApi api;
    private WeatherDao dao;
    @Inject
    public MainRepository(WeatherApi api, WeatherDao dao) {
        this.api = api;
        this.dao = dao;
    }

    public MutableLiveData<Resource<Example>> getCurrentForeCast(String city) {

        MutableLiveData<Resource<Example>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getCurrentForeCast(city, "5d1469e6f801815a95180f4088719ef2", "metric").enqueue(new Callback<Example>() {
            @Override
            public void onResponse(@NonNull Call<Example> call, @NonNull Response<Example> response) {
                if (response.isSuccessful() && response.body() != null){
                    liveData.setValue(Resource.success(response.body()));
                    dao.insertAll(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Example> call, @NonNull Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }

    public MutableLiveData<Resource<List<forecast>>> getDailyForeCast(String city) {
        MutableLiveData<Resource<List<forecast>>> dailyLiveData = new MutableLiveData<>();
        dailyLiveData.setValue(Resource.loading());
        api.getDailyForeCast(city, "5d1469e6f801815a95180f4088719ef2").enqueue(new Callback<List<forecast>>() {
            @Override
            public void onResponse(@NonNull Call<List<forecast>> call, @NonNull Response<List<forecast>> response) {
                if (response.isSuccessful() && response.body() != null){
                    dailyLiveData.setValue(Resource.success(response.body()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<forecast>> call, @NonNull Throwable t) {
                dailyLiveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
       return dailyLiveData;
    }
}