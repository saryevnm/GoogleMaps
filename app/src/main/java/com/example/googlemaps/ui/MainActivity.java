package com.example.googlemaps.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.example.googlemaps.App;
import com.example.googlemaps.domain.models.LatLngm;
import com.example.googlemaps.R;
import com.example.googlemaps.databinding.ActivityMainBinding;
import com.example.googlemaps.domain.preference.Prefs;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private GoogleMap map;
    private ActivityMainBinding ui;
    private LatLngm latLngm;
    private final ArrayList<LatLng> markers = new ArrayList<>();
    private final ArrayList<LatLng> polylineMarkers = new ArrayList<>();
    private final ArrayList<LatLng> polygoneMarkers = new ArrayList<>();
    private final static int PERMISSION_ACCESS_CODE = 5;
    private final static int id = 1;
    private final PolygonOptions polygonOptions = new PolygonOptions();
    private final PolylineOptions polylineOptions = new PolylineOptions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null){
            mapFragment.getMapAsync(this);
        }
        setupListeners();
    }

    private void initDataRoom() {
        if(App.getFilmRepository().getLatLngs(id) != null){
            latLngm = App.getFilmRepository().getLatLngs(id);
            polygoneMarkers.addAll(latLngm.getPolygonMarkers());
            polylineMarkers.addAll(latLngm.getPolylineMarkers());
            markers.addAll(latLngm.getLatLngList());
            for (LatLng latLng: latLngm.getLatLngList()) {
                MarkerOptions  markerOptions = new MarkerOptions()
                        .position(latLng);
                map.addMarker(markerOptions);
            }
        }
    }

    private void initPrefs() {
        Prefs.getInstance(getApplicationContext());
        if (Prefs.getInstance().isPolygonUsed() && !(polygoneMarkers.isEmpty()) ){
            polygonOptions.addAll(polygoneMarkers);
            map.addPolygon(polygonOptions);
        }else if (Prefs.getInstance().isPolylineUsed() && !(polylineMarkers.isEmpty())){
            polylineOptions.addAll(polylineMarkers);
            map.addPolyline(polylineOptions);
        }
    }

    private void setupListeners() {
        ui.hybridMap.setOnClickListener(v -> { map.setMapType(GoogleMap.MAP_TYPE_HYBRID);});
        ui.normalMap.setOnClickListener(v -> { map.setMapType(GoogleMap.MAP_TYPE_NORMAL);});
        ui.polygon.setOnClickListener(v -> {
            Prefs.getInstance().polygonUsed();
            polygoneMarkers.addAll(markers);
            polygonOptions.addAll(markers);
            map.addPolygon(polygonOptions);
        });
        ui.polyline.setOnClickListener(v -> {
            Prefs.getInstance().polylineUsed();
            polylineMarkers.addAll(markers);
            polylineOptions.addAll(markers);
            map.addPolyline(polylineOptions);
        });
        ui.clear.setOnClickListener(v -> {
            map.clear();
            App.getFilmRepository().deleteLatLng(latLngm);
        });
        ui.btnSave.setOnClickListener(v -> {
            App.getFilmRepository().addLatLng(new LatLngm(id,markers,polygoneMarkers,polylineMarkers));
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMapClickListener(this);
        CameraPosition position = CameraPosition.fromLatLngZoom(new LatLng(42.8796606,74.6183954),17.96F);
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
        map.animateCamera(update);
        ActivityCompat.requestPermissions(this
                ,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}
                ,PERMISSION_ACCESS_CODE);
        initDataRoom();
        initPrefs();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_ACCESS_CODE
                && ContextCompat
                .checkSelfPermission(this
                        ,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager
                .PERMISSION_GRANTED){
            map.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        markers.add(latLng);
        MarkerOptions  markerOptions = new MarkerOptions()
                .position(latLng);
        map.addMarker(markerOptions);
    }

}