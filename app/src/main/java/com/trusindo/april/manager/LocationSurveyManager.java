package com.trusindo.april.manager;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by jakalesmana on 10/14/17.
 */

public class LocationSurveyManager {

    private static LocationSurveyManager instance;
    private LocationSurveyManager(){}

    private Location deviceLocation;
    private Location surveyLocation;

    public static LocationSurveyManager getInstance() {
        if (instance == null) {
            instance = new LocationSurveyManager();
        }

        return instance;
    }

    public void setDeviceLocation(Location loc) {
        deviceLocation = loc;
    }

    public Location getDeviceLocation() {
        return deviceLocation;
    }

    public void setSurveyLocation(Location loc) {
        surveyLocation = loc;
    }

    public Location getSurveyLocation() {
        return surveyLocation;
    }

}
