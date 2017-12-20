package com.trusindo.april.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentityprovider.AmazonCognitoIdentityProviderClient;
import com.trusindo.april.Constants.Constant;
import com.trusindo.april.model.User;

/**
 * Created by jakalesmana on 9/12/17.
 */

public class UserLoginStateManager {

    private static final String USER_FCM_TOKEN = "april.fcm.token";
    private static final String USER_FIELD = "april.user";
    private static UserLoginStateManager instance;
    private SharedPreferences mPreferences;
    private User user;
    private CognitoUserPool userPool;

    private UserLoginStateManager() {
    }

    public static UserLoginStateManager getInstance() {
        if (instance == null) {
            instance = new UserLoginStateManager();
        }
        return instance;
    }

    public void setup(Context context) {
        this.mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (!getUserPref().isEmpty()) {
            this.user = User.fromJson(getUserPref());
        }

        ClientConfiguration clientConfiguration = new ClientConfiguration();

        AmazonCognitoIdentityProviderClient identityProviderClient = new AmazonCognitoIdentityProviderClient(new AnonymousAWSCredentials(), clientConfiguration);
        identityProviderClient.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));

        userPool = new CognitoUserPool(context,
                Constant.COGNITO_POOL_ID,
                Constant.COGNITO_CLIENT_ID,null, identityProviderClient);
    }

    public void update(User user) {
        this.user = user;
        SharedPreferences.Editor editor = this.mPreferences.edit();
        editor.putString(USER_FIELD, user.toJson());
        editor.apply();
    }

    public void setFCMToken(String token) {
        SharedPreferences.Editor editor = this.mPreferences.edit();
        editor.putString(USER_FCM_TOKEN, token);
        editor.apply();
    }

    public CognitoUserPool getUserPool() {
        return userPool;
    }

    public String getFCMToken() {
        return this.mPreferences.getString(USER_FCM_TOKEN, "");
    }

    private String getUserPref() {
        return this.mPreferences.getString(USER_FIELD, "");
    }

    public User getUser() {
        return this.user;
    }

    public boolean isLogin() {
        return this.user != null;
    }

    public void logout() {
        this.mPreferences.edit().remove(USER_FIELD).apply();
        this.user = null;
    }

}
