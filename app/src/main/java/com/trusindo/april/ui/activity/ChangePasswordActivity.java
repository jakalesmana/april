package com.trusindo.april.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.trusindo.april.R;

/**
 * Created by jakalesmana on 20/12/17.
 */

public class ChangePasswordActivity extends BaseActivity {

    private EditText txtName, txtNewPassword, txtConfirmNewPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        txtName = findViewById(R.id.txtName);
        txtNewPassword = findViewById(R.id.txtNewPassword);
        txtConfirmNewPassword = findViewById(R.id.txtConfirmNewPassword);
        Button btnSubmit = findViewById(R.id.btnUpdate);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                validateAndUpdate();
            }
        });
    }

    private void validateAndUpdate() {
        txtName.setError(null);
        txtNewPassword.setError(null);
        txtConfirmNewPassword.setError(null);

        if (txtName.getText().toString().isEmpty()) {
            txtName.setError(getString(R.string.error_empty_data));
            txtName.requestFocus();
            return;
        }

        if (txtNewPassword.getText().toString().isEmpty()) {
            txtNewPassword.setError(getString(R.string.error_empty_data));
            txtNewPassword.requestFocus();
            return;
        }

        if (!txtNewPassword.getText().toString().equals(txtConfirmNewPassword.getText().toString())) {
            Toast.makeText(this, "Konfirmasi password tidak sesuai", Toast.LENGTH_SHORT).show();
            txtConfirmNewPassword.requestFocus();
            return;
        }

        Intent i = new Intent();
        i.putExtra("name", txtName.getText().toString());
        i.putExtra("password", txtNewPassword.getText().toString());
        setResult(Activity.RESULT_OK, i);
        finish();

    }
}
