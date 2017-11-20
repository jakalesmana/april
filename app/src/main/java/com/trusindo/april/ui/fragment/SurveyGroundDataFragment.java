package com.trusindo.april.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.Toast;

import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;
import com.trusindo.april.R;
import com.trusindo.april.utils.AppUtils;

/**
 * Created by jakalesmana on 10/10/17.
 */

public class SurveyGroundDataFragment extends BaseFragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_survey_ground_data, container, false);

        EditText txtLineFront = v.findViewById(R.id.txtLineFront);
        EditText txtLineBehind = v.findViewById(R.id.txtLineBehind);
        EditText txtLineLeft = v.findViewById(R.id.txtLineLeft);
        EditText txtLineRight = v.findViewById(R.id.txtLineRight);
        EditText txtElevation = v.findViewById(R.id.txtElevation);

        EditText txtGroundContur = v.findViewById(R.id.txtGroundContur);
        EditText txtGroundKind = v.findViewById(R.id.txtGroundKind);
        EditText txtGroundPosition = v.findViewById(R.id.txtGroundPosition);
        EditText txtGroundOrientation = v.findViewById(R.id.txtGroundOrientation);
        EditText txtGroundShape = v.findViewById(R.id.txtGroundShape);
        EditText txtGroundState = v.findViewById(R.id.txtGroundState);

        txtLineFront.setOnClickListener(this);
        txtLineBehind.setOnClickListener(this);
        txtLineLeft.setOnClickListener(this);
        txtLineRight.setOnClickListener(this);
        txtElevation.setOnClickListener(this);
        txtGroundContur.setOnClickListener(this);
        txtGroundKind.setOnClickListener(this);
        txtGroundPosition.setOnClickListener(this);
        txtGroundOrientation.setOnClickListener(this);
        txtGroundShape.setOnClickListener(this);
        txtGroundState.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.txtLineFront || view.getId() == R.id.txtLineBehind || view.getId() == R.id.txtLineLeft ||
                view.getId() == R.id.txtLineRight) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_data_object_type);
        } else if(view.getId() == R.id.txtElevation) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_elevation_type);
        } else if(view.getId() == R.id.txtGroundContur) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_ground_contur);
        } else if(view.getId() == R.id.txtGroundKind) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_ground_type);
        } else if(view.getId() == R.id.txtGroundPosition) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_ground_position);
        } else if(view.getId() == R.id.txtGroundOrientation) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_mata_angin);
        } else if(view.getId() == R.id.txtGroundShape) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_ground_shape);
        } else if(view.getId() == R.id.txtGroundState) {
            AppUtils.showMenu(getActivity(), (EditText)view, R.array.arr_object_status);
        }
    }

}
