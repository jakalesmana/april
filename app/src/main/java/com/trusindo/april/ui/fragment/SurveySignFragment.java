package com.trusindo.april.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.trusindo.april.R;
import com.trusindo.april.ui.activity.SignaturePadActivity;
import com.trusindo.april.utils.AppUtils;

/**
 * Created by jakalesmana on 10/10/17.
 */

public class SurveySignFragment extends BaseFragment {

    private static final int SIGN_REQ_CODE = 1;

    private ImageView mImgSignature;
    private Button mBtnSignature;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_survey_sign, container, false);

        mImgSignature = v.findViewById(R.id.imgSignature);
        mBtnSignature = v.findViewById(R.id.btnSignature);

        mBtnSignature.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                goToSignaturePad();
            }
        });
        return v;
    }

    private void goToSignaturePad() {
        Intent i = new Intent(getActivity(), SignaturePadActivity.class);
        startActivityForResult(i, SIGN_REQ_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_REQ_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String signPath = data.getStringExtra("signaturePath");
                Bitmap bmp = AppUtils.getBitmapFromInternalStorage(getContext(), signPath);
                if (bmp != null) {
                    mImgSignature.setImageBitmap(bmp);
                }
            }
        }
    }
}
