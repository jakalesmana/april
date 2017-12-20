package com.trusindo.april.ui.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.google.android.gms.maps.model.LatLng;
import com.trusindo.april.Constants.Constant;
import com.trusindo.april.R;
import com.trusindo.april.manager.LocationSurveyManager;
import com.trusindo.april.manager.UserLoginStateManager;
import com.trusindo.april.utils.LocationUtils;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.OnReverseGeocodingListener;
import io.nlopez.smartlocation.SmartLocation;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private int accuracyIndex = Constant.LOCATION_ACCURACY_INDEX_LEVEL;
    private int locationUpdateCounter;

    @SuppressWarnings("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView txtName = navigationView.getHeaderView(0).findViewById(R.id.txtMenuName);
        txtName.setText(UserLoginStateManager.getInstance().getUser().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (LocationUtils.verifyLocationPermissions(this)) {
            if (LocationUtils.checkLocationServiceAvailable(this)) {
                updateLocation();
            }
        }
    }

    private void updateLocation() {
        Log.d("location", "Location update start");
        SmartLocation.with(this).location().oneFix()
                .start(new OnLocationUpdatedListener() {
                    public void onLocationUpdated(Location location) {
                        if (location != null) {

                            Log.d("location", "Location updated from smartloc:" + location.getLatitude() + ", " + location.getLongitude());

//                            StringBuilder builder = new StringBuilder();

//                            TextView txtTest = (TextView) findViewById(R.id.txtTest);
//                            builder.append("location lat:::: " + location.getLatitude() + "\n");
//                            builder.append("location long:::: " + location.getLongitude() + "\n");
//                            builder.append("location accuracy:::: " + location.getAccuracy() + "\n");
//                            builder.append("location bearing:::: " + location.getBearing() + "\n");
//                            builder.append("location time:::: " + location.getTime() + "\n");
//                            builder.append("location altitude:::: " + location.getAltitude() + "\n");
//                            builder.append("location provider:::: " + location.getProvider() + "\n\n\n");
//                            txtTest.setText(builder.toString());

                            if (location.getAccuracy() <= accuracyIndex) {
                                LocationSurveyManager.getInstance().setDeviceLocation(location);
                                getAllAddress(location);
                            } else {
                                if (locationUpdateCounter <= Constant.LOCATION_ACCURACY_INDEX_COUNTER) {
                                    locationUpdateCounter += 1;
                                } else {
                                    accuracyIndex += Constant.LOCATION_ACCURACY_INDEX_LEVEL;
                                }

                                updateLocation();
                            }

                        }
                    }
                });
    }

    private void getAllAddress(Location location) {
        LocationUtils.reverseGeocoding(this, location, new OnReverseGeocodingListener() {
            public void onAddressResolved(Location location, List<Address> list) {
                for (int i=0; i<list.size(); i++) {
                    Log.d("addresses", "Address: " + list.get(i).toString());

//                    TextView txtTest = (TextView) findViewById(R.id.txtTest2);
//                    txtTest.setText("address:::: " + list.get(i).toString());
                }
            }
        });
    }

    @Override
    protected void onStop() {
        SmartLocation.with(this).location().stop();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_building) {
            Intent i = new Intent(this, SurveyPageActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_apartment) {

        } else if (id == R.id.nav_heavy_equipment) {

        } else if (id == R.id.nav_logout) {
            confirmLogout();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void confirmLogout() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Anda yakin?")
                .setContentText("Keluar dari aplikasi mengharuskan Anda untuk login kembali.")
                .setConfirmText("Keluar")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        CognitoUserPool pool = UserLoginStateManager.getInstance().getUserPool();
                        pool.getCurrentUser().signOut();

                        UserLoginStateManager.getInstance().logout();

                        Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                })
                .show();
    }
}
