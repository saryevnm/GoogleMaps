package com.example.googlemaps;

import android.app.Application;

import androidx.room.Room;

import com.example.googlemaps.domain.repo.FilmRepository;
import com.example.googlemaps.domain.utils.Converters;
import com.example.googlemaps.framework.local.AppDatabase;
import com.example.googlemaps.framework.local.RoomLatLngDataSource;

public class App  extends Application {

    private static FilmRepository filmRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase room = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "settings")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        RoomLatLngDataSource roomLatLngDataSource = new RoomLatLngDataSource(room.getLatLngDao());
        filmRepository = new FilmRepository(roomLatLngDataSource);
    }

    public static FilmRepository getFilmRepository(){
        return filmRepository;
    }
}
