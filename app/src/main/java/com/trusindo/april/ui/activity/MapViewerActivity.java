package com.trusindo.april.ui.activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.trusindo.april.R;
import com.trusindo.april.app.AprilApp;
import com.trusindo.april.manager.LocationSurveyManager;

/**
 * Created by jakalesmana on 10/14/17.
 */

public class MapViewerActivity extends BaseActivity implements OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    private GoogleMap mMap;
    private LocationSurveyManager mManager;
    private LatLng mUpdatedLoc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_viewer);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mManager = LocationSurveyManager.getInstance();
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        mUpdatedLoc = new LatLng(mManager.getSurveyLocation().getLatitude(), mManager.getSurveyLocation().getLongitude());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        LatLng me = new LatLng(mManager.getSurveyLocation().getLatitude(), mManager.getSurveyLocation().getLongitude());
        this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(me).zoom(16.0f).build()));
        this.mMap.setOnCameraIdleListener(this);
    }

    @Override
    public void onCameraIdle() {
        mUpdatedLoc = this.mMap.getCameraPosition().target;
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        result.putExtra("locLat", mUpdatedLoc.latitude);
        result.putExtra("locLong", mUpdatedLoc.longitude);
        setResult(RESULT_OK, result);
        super.onBackPressed();
    }
}
