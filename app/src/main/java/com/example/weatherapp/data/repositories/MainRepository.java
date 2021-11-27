package com.example.weatherapp.data.repositories;
import static com.example.weatherapp.App.api;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.App;
import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.models.Example;
import com.example.weatherapp.data.remote.WeatherApi;
import com.example.weatherapp.ui.fragments.MainFragmentArgs;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private WeatherApi api;
    @Inject
    public MainRepository(WeatherApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<Example>> getCurrentForeCast(String city) {

        MutableLiveData<Resource<Example>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getCurrentForeCast(city, "5d1469e6f801815a95180f4088719ef2", "metric").enqueue(new Callback<Example>() {
            @Override
            public void onResponse(@NonNull Call<Example> call, @NonNull Response<Example> response) {
                if (response.isSuccessful() && response.body() != null){
                    liveData.setValue(Resource.success(response.body()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Example> call, @NonNull Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }
}