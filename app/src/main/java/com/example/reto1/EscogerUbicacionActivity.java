package com.example.reto1;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.reto1.databinding.ActivityEscogerUbicacionBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class EscogerUbicacionActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private ActivityEscogerUbicacionBinding binding;
    private LocationManager manager;
    private Marker ubicacion;
    private Button continuarBtn;
    private String direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEscogerUbicacionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        continuarBtn = binding.continuarBtn;
        continuarBtn.setOnClickListener(this::continuar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    private void continuar(View view) {
        if(ubicacion == null){
            Toast.makeText(this, "No has seleccionado la ubicacion del evento", Toast.LENGTH_LONG).show();
        } else{
            Intent databack = new Intent();
            databack.putExtra("ubicacion", direccion);
            setResult(RESULT_OK,databack);
            finish();
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setInitialPos();
        mMap.setOnMapClickListener(this);
    }

    @SuppressLint("MissingPermission")
    public void setInitialPos(){
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null){
            LatLng myPos = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myPos, 17));
        } else{
            Toast.makeText(this, "No hay locacion", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        if(ubicacion == null){
            ubicacion = mMap.addMarker(new MarkerOptions().position(latLng));
        } else{
            ubicacion.setPosition(latLng);
        }
        Geocoder g = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = g.getFromLocation(latLng.latitude, latLng.longitude, 1);
            direccion = addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}