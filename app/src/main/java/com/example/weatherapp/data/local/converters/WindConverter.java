package com.example.weatherapp.data.local.converters;
import androidx.room.TypeConverter;

import com.example.weatherapp.data.models.Weather;
import com.example.weatherapp.data.models.Wind;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class WindConverter {

    @TypeConverter
    public static String fromWindToString(Wind wind) {
        if (wind == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>() {}.getType();
        return gson.toJson(wind, type);
    }

    @TypeConverter
    public static Wind fromStringToWind(String str) {
        if (str == null) { return (null); }
        Gson gson = new Gson();
        Type type = new TypeToken<Wind>() {}.getType();
        return gson.fromJson(str, type);
    }
}
