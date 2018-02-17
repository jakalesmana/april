package com.trusindo.april.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.trusindo.april.R;
import com.trusindo.april.manager.LocationSurveyManager;
import com.trusindo.april.manager.UserLoginStateManager;
import com.trusindo.april.ui.composite.SurveyPagerAdapter;
import com.trusindo.april.utils.AppUtils;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by jakalesmana on 10/10/17.
 */

public class SurveyPageActivity extends BaseActivity {

    private LocationSurveyManager mManager;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mViewPager = findViewById(R.id.viewPagerSurvey);

        SurveyPagerAdapter vpAdapter = new SurveyPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(vpAdapter);

        mManager = LocationSurveyManager.getInstance();
        mManager.setSurveyLocation(mManager.getDeviceLocation());

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                mViewPager.setOffscreenPageLimit(4);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

//        setupTabBar();
        setupMaterialTabBar();
    }

    private void setupMaterialTabBar() {
        final int[] tab_icons = {
                R.mipmap.ic_terrain,
                R.mipmap.ic_building,
                R.mipmap.ic_location_env,
                R.mipmap.ic_photo,
                R.mipmap.ic_assignment,
                R.mipmap.ic_location_on,
                R.mipmap.ic_sign};

        SmartTabLayout tab = findViewById(R.id.vpTabLayout);

        final LayoutInflater inflater = LayoutInflater.from(tab.getContext());
        tab.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                ImageView icon = (ImageView) inflater.inflate(R.layout.custom_tab_icon, container, false);
                icon.setImageDrawable(ContextCompat.getDrawable(SurveyPageActivity.this, tab_icons[position]));
                return icon;
            }
        });

        tab.setViewPager(mViewPager);
    }

//    private void setupTabBar() {
//        final String[] colors = getResources().getStringArray(R.array.tab_colors);
//        int[] tab_icons = {
//                R.mipmap.ic_assignment,
//                R.mipmap.ic_assignment,
//                R.mipmap.ic_assignment,
//                R.mipmap.ic_assignment,
//                R.mipmap.ic_image_object,
//                R.mipmap.ic_location_pick,
//                R.mipmap.ic_sign_tab};
//
//        final String[] titles = {
//                getString(R.string.survey_object_data),
//                getString(R.string.survey_object_ground),
//                getString(R.string.survey_object_building),
//                getString(R.string.survey_object_environmrnt),
//                getString(R.string.survey_object_pic),
//                getString(R.string.survey_object_loc),
//                getString(R.string.survey_object_sign)
//        };
//
//        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
//        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
//
//        for (int i = 0; i < 5; i++) {
//            models.add(
//                    new NavigationTabBar.Model.Builder(
//                            getSupportDrawable(tab_icons[i]),
//                            Color.parseColor(colors[i]))
//                            .title(titles[i])
//                            .build()
//            );
//        }
//
//        navigationTabBar.setModels(models);
//        navigationTabBar.setViewPager(mViewPager, 0);
//    }

    private Drawable getSupportDrawable(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getDrawable(id);
        } else {
            return getResources().getDrawable(id);
        }
    }
    @Override
    protected void onDestroy() {
        mManager.setSurveyLocation(null);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    private void showExitDialog() {
//        AlertDialog.Builder builder;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
//        } else {
//            builder = new AlertDialog.Builder(this);
//        }
//        builder.setTitle("")
//                .setMessage(getString(R.string.exit_confirmation))
//                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                })
//                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {}
//                })
//                .setIcon(android.R.drawable.ic_dialog_info)
//                .show();


        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Keluar Pengisian Data")
                .setContentText(getString(R.string.exit_confirmation))
                .setConfirmText("Keluar")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        finish();
                    }
                })
                .show();
    }
}
