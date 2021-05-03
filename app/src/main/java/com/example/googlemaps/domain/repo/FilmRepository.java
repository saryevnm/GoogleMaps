package com.example.googlemaps.domain.repo;

import com.example.googlemaps.domain.models.LatLngm;
import com.example.googlemaps.domain.source.LatLngSource;

import java.util.List;

public class FilmRepository {

    private final LatLngSource filmSource;

    public FilmRepository(LatLngSource filmSource){
        this.filmSource = filmSource;
    }

    public LatLngm getLatLngs(int id){
        return filmSource.getLatLng(id);
    }
    public void addLatLng(LatLngm latLngs){
        filmSource.addLatLng(latLngs);
    }
    public void deleteLatLng(LatLngm latLngm){
        filmSource.deleteLatlng(latLngm);
    }


}
