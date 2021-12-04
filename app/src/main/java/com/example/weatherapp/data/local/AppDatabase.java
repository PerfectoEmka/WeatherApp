package com.example.weatherapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weatherapp.data.local.converters.ListWeatherConverter;
import com.example.weatherapp.data.local.converters.MainConverter;
import com.example.weatherapp.data.models.Example;

@Database(entities = {Example.class}, version = 1)
@TypeConverters({MainConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}
