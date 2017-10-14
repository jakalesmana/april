package com.trusindo.april.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by jakalesmana on 9/12/17.
 */

public class User implements Serializable {

    private String email;
    private String fcmToken;
    private String gender;
    private String name;
    private String token;
    private int uid;

    public User() {
        setName("");
        setEmail("");
        setGender("");
        setUid(-1);
        setToken("");
        setFcmToken("");
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", this.name);
            jsonObject.put("email", this.email);
            jsonObject.put("gender", this.gender);
            jsonObject.put("uid", this.uid);
            jsonObject.put("token", this.token);
            jsonObject.put("fcmToken", this.fcmToken);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static User parseUser(String data) {
        try {
            User user = new User();
            JSONObject jsonObj = new JSONObject(data);
            user.setName(jsonObj.getString("name"));
            user.setEmail(jsonObj.getString("email"));
            user.setGender(jsonObj.getString("gender"));
            user.setUid(jsonObj.getInt("uid"));
            if (jsonObj.has("token")) {
                user.setToken(jsonObj.getString("token"));
            }
            if (!jsonObj.has("fcmToken")) {
                return user;
            }
            user.setFcmToken(jsonObj.getString("fcmToken"));
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
