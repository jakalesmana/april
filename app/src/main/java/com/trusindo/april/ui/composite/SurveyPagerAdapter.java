package com.trusindo.april.ui.composite;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.trusindo.april.R;
import com.trusindo.april.ui.fragment.SurveyBuildingDataFragment;
import com.trusindo.april.ui.fragment.SurveyEnvironmentDataFragment;
import com.trusindo.april.ui.fragment.SurveyGroundDataFragment;
import com.trusindo.april.ui.fragment.SurveyLocFragment;
import com.trusindo.april.ui.fragment.SurveyObjectDataFragment;
import com.trusindo.april.ui.fragment.SurveyPicComparationFragment;
import com.trusindo.april.ui.fragment.SurveyPicObjectFragment;
import com.trusindo.april.ui.fragment.SurveySignFragment;

/**
 * Created by jakalesmana on 10/10/17.
 */

public class SurveyPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

    public SurveyPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SurveyObjectDataFragment();
            case 1:
                return new SurveyGroundDataFragment();
            case 2:
                return new SurveyEnvironmentDataFragment();
            case 3:
                return new SurveyBuildingDataFragment();
            case 4:
                return new SurveyPicObjectFragment();
            case 5:
                return new SurveyLocFragment();
            case 6:
                return new SurveySignFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 7;
    }
}
