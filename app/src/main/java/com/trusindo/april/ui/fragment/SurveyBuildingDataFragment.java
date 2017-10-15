package com.trusindo.april.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trusindo.april.R;

/**
 * Created by jakalesmana on 10/10/17.
 */

public class SurveyBuildingDataFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_survey_building_data, container, false);
        return v;
    }
}
