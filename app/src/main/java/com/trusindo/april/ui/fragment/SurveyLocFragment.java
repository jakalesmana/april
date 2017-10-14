package com.trusindo.april.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import com.trusindo.april.ui.activity.MapViewerActivity;
import com.trusindo.april.utils.LocationUtils;

import java.util.List;

import io.nlopez.smartlocation.OnReverseGeocodingListener;

/**
 * Created by jakalesmana on 10/10/17.
 */

public class SurveyLocFragment extends BaseFragment implements OnMapReadyCallback {

    private static final int LOCATION_UPDATE_INDEX = 1;

    private GoogleMap mMap;
    private LocationSurveyManager mLocManager;
    private EditText txtAddress, txtKelurahan, txtKecamatan, txtCity, txtProvince, txtPostalCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_survey_loc, container, false);
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapSurvey)).getMapAsync(this);
        mLocManager = LocationSurveyManager.getInstance();

        txtAddress = v.findViewById(R.id.txtAddress);
        txtKelurahan = v.findViewById(R.id.txtAddressKelurahan);
        txtKecamatan = v.findViewById(R.id.txtAddressKecamatan);
        txtCity = v.findViewById(R.id.txtAddressKota);
        txtProvince = v.findViewById(R.id.txtAddressProvince);
        txtPostalCode = v.findViewById(R.id.txtAddressPostalCode);

        Button btnSetLocation= v.findViewById(R.id.btnSetLocation);
        btnSetLocation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                goToMapViewer();
            }
        });

        return v;
    }

    private void goToMapViewer() {
        Intent i = new Intent(getContext(), MapViewerActivity.class);
        startActivityForResult(i, LOCATION_UPDATE_INDEX);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng me;
        this.mMap = googleMap;
        if (mLocManager.getSurveyLocation() == null) {
            me = new LatLng(0.0, 0.0);
        } else {
            me = new LatLng(mLocManager.getSurveyLocation().getLatitude(), mLocManager.getSurveyLocation().getLongitude());
        }

        updateMap(me);

        updateAddressText(mLocManager.getSurveyLocation());
    }

    private void updateAddressText(Location location) {
        LocationUtils.reverseGeocoding(getActivity(), location, new OnReverseGeocodingListener() {
            public void onAddressResolved(Location location, List<Address> list) {
                if (list.size() != 0) {
                    Address adr = list.get(0);
                    txtAddress.setText(adr.getThoroughfare() + " No. " + adr.getFeatureName());
                    txtKecamatan.setText(adr.getLocality());
                    txtCity.setText(adr.getSubAdminArea());
                    txtProvince.setText(adr.getAdminArea());
                    txtPostalCode.setText(adr.getPostalCode());

                    if (adr.getAddressLine(0).split(",").length > 1) {
                        txtKelurahan.setText(adr.getAddressLine(0).split(",")[1].trim());
                    }
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOCATION_UPDATE_INDEX) {
            if (resultCode == Activity.RESULT_OK) {

                double locLat = data.getDoubleExtra("locLat", 0);
                double locLong = data.getDoubleExtra("locLong", 0);

                Location loc = new Location(LocationManager.GPS_PROVIDER);
                loc.setLatitude(locLat);
                loc.setLongitude(locLong);
                mLocManager.setSurveyLocation(loc);

                if (this.mMap != null) {
                    this.mMap.clear();
                    LatLng me = new LatLng(locLat, locLong);
                    updateMap(me);
                    updateAddressText(loc);
                }
            }
        }
    }

    private void updateMap(LatLng loc) {
        this.mMap.addMarker(new MarkerOptions().position(loc).title(getString(R.string.survey_location)).draggable(false));
        this.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(loc).zoom(16.0f).build()));
    }
}
