package com.example.weatherapp.data.local.converters;
import androidx.room.TypeConverter;

import com.example.weatherapp.data.models.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListWeatherConverter {

    @TypeConverter
    public static String fromWeatherToString(List<Weather> weather) {
        if (weather == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather>>() {}.getType();
        return gson.toJson(weather, type);
    }

    @TypeConverter
    public static List<Weather> fromStringToWeather(String str) {
        if (str == null) { return (null); }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Weather>>() {}.getType();
        return gson.fromJson(str, type);
    }
}
