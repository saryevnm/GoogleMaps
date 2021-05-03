package com.example.googlemaps.domain.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private static Prefs single_instance = null;
    private final SharedPreferences preferences;

    private Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void polygonUsed() {
        preferences.edit().putBoolean("isPolygon", true).apply();
    }

    public boolean isPolygonUsed() {
        return preferences.getBoolean("isPolygon", false);
    }

    public static Prefs getInstance(Context context) {
        return single_instance = new Prefs(context);
    }

    public static Prefs getInstance() {
        return single_instance;
    }

    public void polylineUsed() {
        preferences.edit().putBoolean("isPolyline", true).apply();
    }

    public boolean isPolylineUsed() {
        return preferences.getBoolean("isPolyline", false);
    }

}
