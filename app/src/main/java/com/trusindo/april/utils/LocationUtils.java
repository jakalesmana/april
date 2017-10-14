package com.trusindo.april.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.trusindo.april.R;

import java.util.List;

import io.nlopez.smartlocation.OnReverseGeocodingListener;
import io.nlopez.smartlocation.SmartLocation;

/**
 * Created by jakalesmana on 9/12/17.
 */

public class LocationUtils {

    // Storage Permissions
    private static final int REQUEST_LOCATION = 1;
    private static String[] PERMISSIONS_LOCATION = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    public static boolean isGooglePlayServicesAvailable(Context context) {

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(context);
        if(status != ConnectionResult.SUCCESS) {
            if(googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog((Activity) context, status, 2404).show();
            }
            return false;
        }
        return true;
    }

    public static boolean checkLocationServiceAvailable(Context context) {

        // Check if the location services are enabled
        Log.d("tag", "enabled loc service: " + SmartLocation.with(context).location().state().locationServicesEnabled());
        boolean serviceEnabled = SmartLocation.with(context).location().state().locationServicesEnabled();

        // Check if any provider (network or gps) is enabled
        Log.d("tag", "enabled (network or gps): " + SmartLocation.with(context).location().state().isAnyProviderAvailable());
        boolean providerEnabled = SmartLocation.with(context).location().state().isAnyProviderAvailable();

        // Check if GPS is available
        Log.d("tag", "enabled GPS: " + SmartLocation.with(context).location().state().isGpsAvailable());

        // Check if Network is available
        Log.d("tag", "enabled Network: " + SmartLocation.with(context).location().state().isNetworkAvailable());

        // Check if the passive provider is available
        Log.d("tag", "enabled passive provider: " + SmartLocation.with(context).location().state().isPassiveAvailable());

        // Check if the location is mocked
        Log.d("tag", "enabled location mocked: " + SmartLocation.with(context).location().state().isMockSettingEnabled());

        if (!serviceEnabled || !serviceEnabled) {
            showSettingDialog(context);
            return false;
        }

        return true;
    }

    private static void showSettingDialog(final Context context) {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(context.getString(R.string.label_location))
                .setMessage(context.getString(R.string.permission_location))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent viewIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(viewIntent);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    public static boolean verifyLocationPermissions(Activity activity) {

        int permissionCoarse = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permissionFine = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCoarse != PackageManager.PERMISSION_GRANTED || permissionFine != PackageManager.PERMISSION_GRANTED) {
            Log.d("permission", "permission not granted");
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_LOCATION,
                    REQUEST_LOCATION
            );

            return false;
        } else {
            Log.d("permission", "permission granted");
            return true;
        }
    }

    public static void reverseGeocoding(Context context, Location location, OnReverseGeocodingListener listener) {
        SmartLocation.with(context).geocoding().reverse(location, listener);
    }

}
