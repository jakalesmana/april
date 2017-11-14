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
        txtLineFront.setOnClickListener(this);
        txtLineBehind.setOnClickListener(this);
        txtLineLeft.setOnClickListener(this);
        txtLineRight.setOnClickListener(this);
        txtElevation.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.txtLineFront || view.getId() == R.id.txtLineBehind || view.getId() == R.id.txtLineLeft ||
                view.getId() == R.id.txtLineRight) {
            showMenu((EditText)view, R.array.arr_data_object_type);
        } else if(view.getId() == R.id.txtElevation) {
            showMenu((EditText)view, R.array.arr_elevation_type);
        }
    }

    private void showMenu(final EditText txtView, int resArray) {
        final String[] arrs = getResources().getStringArray(resArray);

        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(getActivity(), txtView);

        for (int i = 0; i < arrs.length; i++) {
            droppyBuilder.addMenuItem(new DroppyMenuItem(arrs[i]));
        }

        droppyBuilder.setOnClick(new DroppyClickCallbackInterface() {
            @Override
            public void call(View v, int id) {
                txtView.setText(arrs[id]);
            }
        });

        droppyBuilder.setPopupAnimation(new DroppyFadeInAnimation());
        droppyBuilder.setXOffset(5);
        droppyBuilder.setYOffset(5);
        droppyBuilder.build().show();
    }

}
