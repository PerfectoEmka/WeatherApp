package com.example.weatherapp.data.local.converters;
import androidx.room.TypeConverter;

import com.example.weatherapp.data.models.Main;
import com.example.weatherapp.data.models.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainConverter {

    @TypeConverter
    public static String fromMainToString(Main main) {
        if (main == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Main>() {}.getType();
        return gson.toJson(main, type);
    }

    @TypeConverter
    public static Main fromStringToMain(String str) {
        if (str == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Main>() {}.getType();
        return gson.fromJson(str, type);
    }
}
