package com.example.weatherapp.data.local;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.weatherapp.data.models.Example;
import com.example.weatherapp.data.models.Weather;

import java.util.List;
@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Example list);

    @Query("SELECT * FROM example")
    Example getWeather();
}
