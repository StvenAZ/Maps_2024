package com.example.maps_2024;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        //configurar el mapa
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);

        //40.689211569891306, -74.04445384309386
//-1.0249803481158417, -79.46656036406034
        LatLng posQuevedo = new LatLng(-1.02498, -79.4665);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(posQuevedo)
                .zoom(17)
                .bearing(30) //noreste arriba
                .tilt(10) //punto de vista de la cámara 70 grados
                .build();
        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(camUpd3);
        //anadir circular
        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(-1.0242220, -79.46776)) // Centro del círculo
                .radius(250) // Radio del círculo en metros
                .strokeWidth(8) // Ancho del borde del círculo
                .strokeColor(Color.RED) // Color del borde
                .fillColor(Color.argb(50, 255, 0, 0)); // Color de relleno con opacidad

        mapa.addCircle(circleOptions);

//agregar marcadores

        mapa.addMarker(new MarkerOptions()
                .position(posQuevedo)
                .title("Centro de Quevedo"));
        mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-1.0242220, -79.46776)));
        mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-1.02427, -79.4655)));
        mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-1.025840, -79.46645)));
        mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-1.02543, -79.46795)));
        // capturar el evento click
        mapa.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng point) {
                mapa.addMarker(new MarkerOptions()
                        .position(new LatLng(point.latitude,point.longitude)));
            }
        });
        //pintemos un circulo
    }
}