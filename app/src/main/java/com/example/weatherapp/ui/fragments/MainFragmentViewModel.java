package com.example.weatherapp.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.models.Example;
import com.example.weatherapp.data.repositories.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainFragmentViewModel extends ViewModel {
    private MainRepository repository;
    public LiveData<Resource<Example>> LiveData;

    @Inject
    public MainFragmentViewModel(MainRepository repository) {
        this.repository = repository;
    }

    public void fetchCurrentForeCast(String city) {
        LiveData = repository.getCurrentForeCast(city);
    }
}
