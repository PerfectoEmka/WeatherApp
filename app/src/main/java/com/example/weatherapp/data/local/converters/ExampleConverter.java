package com.example.weatherapp.data.local.converters;

import androidx.room.TypeConverter;

import com.example.weatherapp.data.models.Clouds;
import com.example.weatherapp.data.models.Example;
import com.example.weatherapp.data.models.Weather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ExampleConverter {


    @TypeConverter
    public static String fromExampleToString(Example example) {
        if (example == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Example>() {}.getType();
        return gson.toJson(example, type);
    }

    @TypeConverter
    public static Clouds fromStringToExample(String str) {
        if (str == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Example>() {}.getType();
        return gson.fromJson(str, type);
    }

}
