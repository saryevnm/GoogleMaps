package com.example.googlemaps.framework.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.googlemaps.domain.models.LatLngm;
import com.example.googlemaps.domain.utils.Converters;
import com.example.googlemaps.framework.local.dao.LatLngDao;

@Database(entities = {LatLngm.class},version = 4)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract LatLngDao getLatLngDao();
}
