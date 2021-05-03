package com.example.googlemaps.framework.local;

import com.example.googlemaps.domain.models.LatLngm;
import com.example.googlemaps.domain.source.LatLngSource;
import com.example.googlemaps.framework.local.dao.LatLngDao;

import java.util.List;

public class RoomLatLngDataSource implements LatLngSource {

    private final LatLngDao latLngDao;

    public RoomLatLngDataSource(LatLngDao latLngDao) {
        this.latLngDao = latLngDao;
    }

    @Override
    public LatLngm getLatLng(int id) {
        return latLngDao.getLatLngs(id);
    }

    @Override
    public void addLatLng(LatLngm latLng) {
        latLngDao.insertLatLng(latLng);
    }

    @Override
    public void deleteLatlng(LatLngm latLngm) {
        latLngDao.deleteLanLng(latLngm);
    }
}
