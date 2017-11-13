package com.trusindo.april.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.trusindo.april.R;
import com.trusindo.april.model.SurveyPicture;
import com.trusindo.april.ui.composite.SurveyPicAdapter;
import com.trusindo.april.utils.AppUtils;
import com.trusindo.april.utils.MyGlideEngine;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakalesmana on 10/10/17.
 */

public class SurveyPicComparationFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final int IMAGE_PICKER_REQ_CODE = 1;

    private SurveyPicAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_survey_comparation_picture, container, false);

        ListView lvPic = v.findViewById(R.id.lvEnvPic);
        mAdapter = new SurveyPicAdapter(getContext());
        lvPic.setAdapter(mAdapter);

        Button btnAddPic = v.findViewById(R.id.btnAddPhoto);
        btnAddPic.setOnClickListener(this);
        lvPic.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAddPhoto) {
            //ask permission explicitly

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(AppUtils.verifyStoragePermissions(getActivity())) {
                    startImagePicker();
                }
            } else {
                startImagePicker();
            }
        }
    }

    private void startImagePicker() {
        Matisse.from(this)
                .choose(MimeType.of(MimeType.JPEG))
                .theme(R.style.Matisse_Dracula)
                .countable(true)
                .capture(true)
                .captureStrategy(new CaptureStrategy(true, "com.trusindo.april.fileprovider"))
                .maxSelectable(10)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .imageEngine(new MyGlideEngine())
                .forResult(IMAGE_PICKER_REQ_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_PICKER_REQ_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                List<Uri> uris = Matisse.obtainResult(data);

                List<SurveyPicture> pics = new ArrayList<>();
                for (int i = 0; i < uris.size(); i++) {
                    pics.add(new SurveyPicture(uris.get(i), ""));
                }

                mAdapter.addPics(pics);
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long id) {
        new LovelyTextInputDialog(getContext(), R.style.EditTextTintTheme)
                .setTopColorRes(R.color.colorPrimary)
                .setTitle(R.string.pic_desc)
                .setMessage("")
                .setIcon(R.mipmap.ic_assignment_white_36dp)
                .setInputFilter(R.string.input_error, new LovelyTextInputDialog.TextFilter() {
                    public boolean check(String text) {
                        return !text.trim().isEmpty();
                    }
                })
                .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                    public void onTextInputConfirmed(String text) {
                        mAdapter.getItem(pos).setImageDescription(text);
                        mAdapter.notifyDataSetChanged();
                    }
                })
                .show();
    }
}
