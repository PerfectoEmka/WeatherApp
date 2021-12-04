package com.example.weatherapp.data.local.converters;
import androidx.room.TypeConverter;

import com.example.weatherapp.data.models.Sys;
import com.example.weatherapp.data.models.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SysConverter {

    @TypeConverter
    public static String fromSysToString(Sys sys) {
        if (sys == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Sys>() {}.getType();
        return gson.toJson(sys, type);
    }

    @TypeConverter
    public static Sys fromStringToSys(String str) {
        if (str == null) { return (null); }
        Gson gson = new Gson();
        Type type = new TypeToken<Sys>() {}.getType();
        return gson.fromJson(str, type);
    }
}
