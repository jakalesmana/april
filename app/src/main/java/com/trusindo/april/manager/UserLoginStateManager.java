package com.trusindo.april.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.trusindo.april.model.User;

/**
 * Created by jakalesmana on 9/12/17.
 */

public class UserLoginStateManager {

    private static final String USER_FCM_TOKEN = "april.fcm.token";
    private static final String USER_FIELD = "april.user";
    private static final String WELCOME_OPENED = "april.welcome.opened";
    private static UserLoginStateManager instance;
    private SharedPreferences mPreferences;
    private String tempToken;
    private User user;

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
            this.user = User.parseUser(getUserPref());
        }
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

    public void setWelcomeOpened() {
        SharedPreferences.Editor editor = this.mPreferences.edit();
        editor.putBoolean(WELCOME_OPENED, true);
        editor.apply();
    }

    public boolean isWelcomeOpened() {
        return this.mPreferences.getBoolean(WELCOME_OPENED, false);
    }

    public void setTempToken(String token) {
        this.tempToken = token;
    }

    public String getTempToken() {
        return this.tempToken;
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
