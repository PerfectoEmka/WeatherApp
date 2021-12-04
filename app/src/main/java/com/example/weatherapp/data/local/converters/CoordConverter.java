package com.example.weatherapp.data.local.converters;
import androidx.room.TypeConverter;

import com.example.weatherapp.data.models.Coord;
import com.example.weatherapp.data.models.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CoordConverter {

    @TypeConverter
    public static String fromCoordToString(Coord coord) {
        if (coord == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {}.getType();
        return gson.toJson(coord, type);
    }

    @TypeConverter
    public static Coord fromStringToCoord(String str) {
        if (str == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Coord>() {}.getType();
        return gson.fromJson(str, type);
    }
}
