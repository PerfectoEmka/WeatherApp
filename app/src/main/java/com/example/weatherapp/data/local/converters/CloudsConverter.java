package com.example.weatherapp.data.local.converters;
import androidx.room.TypeConverter;

import com.example.weatherapp.data.models.Clouds;
import com.example.weatherapp.data.models.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CloudsConverter {

    @TypeConverter
    public static String fromCloudsToString(Clouds clouds) {
        if (clouds == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>() {}.getType();
        return gson.toJson(clouds, type);
    }

    @TypeConverter
    public static Clouds fromStringToClouds(String str) {
        if (str == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>() {}.getType();
        return gson.fromJson(str, type);
    }
}
