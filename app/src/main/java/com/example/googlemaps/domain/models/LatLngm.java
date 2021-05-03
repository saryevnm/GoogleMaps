package com.example.googlemaps.domain.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.googlemaps.domain.utils.Converters;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

@Entity
public class LatLngm {

    @PrimaryKey
    private int id;

    @TypeConverters(Converters.class)
    private ArrayList<LatLng> markers;

    @TypeConverters(Converters.class)
    private ArrayList<LatLng> polygonMarkers;

    @TypeConverters(Converters.class)
    private ArrayList<LatLng> polylineMarkers;

    public LatLngm(int id, ArrayList<LatLng> markers,ArrayList<LatLng> polygonMarkers,ArrayList<LatLng> polylineMarkers) {
        this.id = id;
        this.markers = markers;
        this.polygonMarkers = polygonMarkers;
        this.polylineMarkers = polylineMarkers;
    }

    public ArrayList<LatLng> getLatLngList() {
        return markers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<LatLng> getMarkers() {
        return markers;
    }

    public void setMarkers(ArrayList<LatLng> markers) {
        this.markers = markers;
    }

    public ArrayList<LatLng> getPolygonMarkers() {
        return polygonMarkers;
    }

    public void setPolygonMarkers(ArrayList<LatLng> polygonMarkers) {
        this.polygonMarkers = polygonMarkers;
    }

    public ArrayList<LatLng> getPolylineMarkers() {
        return polylineMarkers;
    }

    public void setPolylineMarkers(ArrayList<LatLng> polylineMarkers) {
        this.polylineMarkers = polylineMarkers;
    }
}
