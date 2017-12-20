package com.trusindo.april.model;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by jakalesmana on 9/12/17.
 */

public class User implements Serializable {

    private String userName;
    private String email;
    private String fcmToken;
    private String name;
    private String token;

    public User() {
        setUserName("");
        setName("");
        setEmail("");
        setToken("");
        setFcmToken("");
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static User fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }

    public String getFcmToken() {
        return this.fcmToken;
    }

    public void setFcmToken(String token) {
        this.fcmToken = token;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
