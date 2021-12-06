package com.example.weatherapp.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.models.Example;
import com.example.weatherapp.data.models.daily.forecast;
import com.example.weatherapp.data.repositories.MainRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainFragmentViewModel extends ViewModel {
    private MainRepository repository;
    public LiveData<Resource<Example>> liveData;
    //public LiveData<Resource<List<forecast>>> dailyLiveData;

    @Inject
    public MainFragmentViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public void fetchCurrentForeCast(String lat, String lon) {
        liveData = repository.getCurrentForeCast(lat, lon);
    }

    /*public void fetchDailyForeCast(String city){
        dailyLiveData = repository.getDailyForeCast(city);
    }*/
}
