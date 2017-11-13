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
        txtLineFront.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.txtLineFront) {
            showMenu(view, R.menu.data_object_type);
        }
    }

    private void showMenu(View view, int resMenu) {

    }

}
