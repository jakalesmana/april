package com.trusindo.april.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.util.CognitoServiceConstants;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentityprovider.AmazonCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidentityprovider.model.UserNotFoundException;
import com.trusindo.april.Constants.Constant;
import com.trusindo.april.R;
import com.trusindo.april.manager.UserLoginStateManager;
import com.trusindo.april.model.User;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity  {

    private final int CHANGE_PASSWORD_CHALLENGE_CODE = 1;

    private AutoCompleteTextView txtUsername;
    private EditText txtPassword;
    private ChallengeContinuation challengeContinuation;
    private SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsername = findViewById(R.id.txtUsername);

        txtPassword = findViewById(R.id.txtPassword);
        txtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {

        txtUsername.setError(null);
        txtPassword.setError(null);

        String uname = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(uname)) {
            txtUsername.setError(getString(R.string.error_empty_data));
            focusView = txtUsername;
            cancel = true;
        }
        if (TextUtils.isEmpty(password)) {
            txtPassword.setError(getString(R.string.error_empty_data));
            focusView = txtPassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
            return;
        }

        doLogin();
    }

    private void doLogin() {
        showLoading();
        CognitoUserPool userPool = UserLoginStateManager.getInstance().getUserPool();
        CognitoUser user = userPool.getUser(txtUsername.getText().toString());

        loginUser(user);
    }

    private void loginUser(final CognitoUser user) {
        // Callback handler for the sign-in process
        AuthenticationHandler authenticationHandler = new AuthenticationHandler() {

            @Override
            public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
                // Sign-in was successful, cognitoUserSession will contain tokens for the user
                getUserDetail(user);
            }

            @Override
            public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {

                // The API needs user sign-in credentials to continue
                AuthenticationDetails authenticationDetails = new AuthenticationDetails(userId, txtPassword.getText().toString(), null);

                // Pass the user sign-in credentials to the continuation
                authenticationContinuation.setAuthenticationDetails(authenticationDetails);

                // Allow the sign-in to continue
                authenticationContinuation.continueTask();
            }

            @Override
            public void getMFACode(MultiFactorAuthenticationContinuation continuation) {
            }

            @Override
            public void authenticationChallenge(ChallengeContinuation continuation) {
                challengeContinuation = continuation;
                startActivityForResult(new Intent(LoginActivity.this, ChangePasswordActivity.class), CHANGE_PASSWORD_CHALLENGE_CODE);
            }

            @Override
            public void onFailure(Exception exception) {
                // Sign-in failed, check exception for the cause

                dismissLoading();

                exception.printStackTrace();
                Toast.makeText(LoginActivity.this, "Failed sign in: " + exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        };

        // Sign in the user
        user.getSessionInBackground(authenticationHandler);
    }

    private void getUserDetail(final CognitoUser user) {
        GetDetailsHandler handler = new GetDetailsHandler() {
            @Override
            public void onSuccess(final CognitoUserDetails details) {
                // Successfully retrieved user details
                String name = details.getAttributes().getAttributes().get("given_name");
                String email = details.getAttributes().getAttributes().get("email");
                String userName = user.getUserId();

                User user = new User();
                user.setEmail(email);
                user.setName(name);
                user.setUserName(userName);

                UserLoginStateManager.getInstance().update(user);

                goToHome();

                dismissLoading();

                finish();
            }

            @Override
            public void onFailure(final Exception exception) {
                // Failed to retrieve the user details, probe exception for the cause

                dismissLoading();

                exception.printStackTrace();
                Toast.makeText(LoginActivity.this, "Failed get detail", Toast.LENGTH_LONG).show();
            }
        };
        user.getDetailsInBackground(handler);
    }

    private void goToHome() {
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHANGE_PASSWORD_CHALLENGE_CODE) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String newPassword = data.getStringExtra("password");

                challengeContinuation.setChallengeResponse(CognitoServiceConstants.CHLG_RESP_NEW_PASSWORD, newPassword);
                challengeContinuation.setChallengeResponse(CognitoServiceConstants.CHLG_PARAM_USER_ATTRIBUTE_PREFIX + "given_name", name);
                challengeContinuation.continueTask();
                challengeContinuation = null;
            }
        } else {
            dismissLoading();
        }
    }

    private void showLoading() {
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void dismissLoading() {
        if (pDialog != null) {
            pDialog.dismissWithAnimation();
        }
    }
}

