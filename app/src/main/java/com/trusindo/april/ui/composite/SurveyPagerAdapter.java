package com.trusindo.april.ui.composite;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.trusindo.april.R;
import com.trusindo.april.ui.fragment.SurveyLocFragment;
import com.trusindo.april.ui.fragment.SurveyObjectDataFragment;
import com.trusindo.april.ui.fragment.SurveyPicEnvironmentFragment;
import com.trusindo.april.ui.fragment.SurveyPicObjectFragment;
import com.trusindo.april.ui.fragment.SurveySignFragment;
import com.trusindo.april.ui.tab.TabBarView;

/**
 * Created by jakalesmana on 10/10/17.
 */

public class SurveyPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;

//    private int[] tab_icons = {R.mipmap.ic_launcher_round,
//            android.R.mipmap.sym_def_app_icon,
//            android.R.drawable.btn_plus,
//            android.R.drawable.btn_star_big_on,
//            android.R.drawable.btn_plus};

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
                return new SurveyPicObjectFragment();
            case 2:
                return new SurveyPicEnvironmentFragment();
            case 3:
                return new SurveyLocFragment();
            case 4:
                return new SurveySignFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.survey_object_data);
            case 1:
                return context.getString(R.string.pic_object);
            case 2:
                return context.getString(R.string.pic_environment);
            case 3:
                return context.getString(R.string.survey_object_loc);
            case 4:
                return context.getString(R.string.survey_object_sign);
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

//    @Override
//    public int getIconResId(int position) {
//        return tab_icons[position];
//    }
}
