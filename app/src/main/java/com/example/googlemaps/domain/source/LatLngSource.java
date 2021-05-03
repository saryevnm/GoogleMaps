package com.example.googlemaps.domain.source;

import com.example.googlemaps.domain.models.LatLngm;

import java.util.List;

public interface LatLngSource {

    LatLngm getLatLng(int id);
    void addLatLng(LatLngm latLng);
    void deleteLatlng(LatLngm latLngm);
}
