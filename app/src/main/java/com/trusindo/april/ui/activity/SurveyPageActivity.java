package com.trusindo.april.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trusindo.april.R;
import com.trusindo.april.manager.LocationSurveyManager;
import com.trusindo.april.ui.composite.SurveyPagerAdapter;
import com.trusindo.april.ui.tab.TabBarView;
import com.trusindo.april.utils.AppUtils;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

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

        mViewPager = (ViewPager) findViewById(R.id.viewPagerSurvey);

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

        setupTabBar();
    }

    private void setupTabBar() {
        final String[] colors = getResources().getStringArray(R.array.tab_colors);
        int[] tab_icons = {R.mipmap.ic_assignment,
                R.mipmap.ic_image_object,
                R.mipmap.ic_image_env,
                R.mipmap.ic_location_pick,
                R.mipmap.ic_sign_tab};

        final String[] titles = {
                getString(R.string.survey_object_data),
                getString(R.string.survey_object_pic),
                getString(R.string.survey_object_pic),
                getString(R.string.survey_object_loc),
                getString(R.string.survey_object_sign)
        };

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            models.add(
                    new NavigationTabBar.Model.Builder(
                            getSupportDrawable(tab_icons[i]),
                            Color.parseColor(colors[i]))
                            .title(titles[i])
                            .build()
            );
        }

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(mViewPager, 0);

//        navigationTabBar.post(new Runnable() {
//            public void run() {
//                ((ViewGroup.MarginLayoutParams) mViewPager.getLayoutParams()).topMargin = (int) -navigationTabBar.getBadgeMargin();
//                mViewPager.requestLayout();
//            }
//        });
    }

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
}
