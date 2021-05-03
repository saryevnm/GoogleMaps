package com.example.googlemaps.domain.utils;

import androidx.room.TypeConverter;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {

    @TypeConverter
    public static ArrayList<LatLng> fromString(String value) {
        Type listType = new TypeToken<ArrayList<LatLng>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<LatLng> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

}
