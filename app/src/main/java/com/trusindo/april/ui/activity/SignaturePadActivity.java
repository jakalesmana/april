package com.trusindo.april.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.trusindo.april.R;
import com.trusindo.april.utils.AppUtils;

/**
 * Created by jakalesmana on 10/14/17.
 */

public class SignaturePadActivity extends BaseActivity implements View.OnClickListener {

    private SignaturePad mPad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature_pad);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mPad = (SignaturePad) findViewById(R.id.signaturePad);
        View btnSave = findViewById(R.id.layoutSave);
        View btnDelete = findViewById(R.id.layoutDelete);

        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.layoutSave) {
            String signPath = AppUtils.saveToInternalStorage(SignaturePadActivity.this, "" + System.currentTimeMillis(), mPad.getTransparentSignatureBitmap());

            Intent intentResult = new Intent();
            intentResult.putExtra("signaturePath", signPath);
            setResult(RESULT_OK, intentResult);

            finish();
        } else if (view.getId() == R.id.layoutDelete) {
            mPad.clear();
        }
    }
}
