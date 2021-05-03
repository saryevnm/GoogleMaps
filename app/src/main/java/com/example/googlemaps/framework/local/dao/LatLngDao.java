package com.example.googlemaps.framework.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.googlemaps.domain.models.LatLngm;

import java.util.List;

@Dao
public interface LatLngDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLatLng(LatLngm latLng);


    @Query("SELECT * FROM LatLngm WHERE id =:id ")
    LatLngm getLatLngs(int id);

    @Delete
    void deleteLanLng(LatLngm latLngm);


}
