package com.trusindo.april.tools;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.trusindo.april.error.APIError;
import com.trusindo.april.manager.UserLoginStateManager;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by jakalesmana on 9/12/17.
 */

public class HTTPIntConnection {
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 5000;
    private static final int WRITE_TIMEOUT = 10000;
    private static HTTPIntConnection instance;
    private static Handler mainHandler;

    private HTTPIntConnection() {
    }

    public static HTTPIntConnection getInstance() {
        if (instance == null) {
            instance = new HTTPIntConnection();
            mainHandler = new Handler(Looper.getMainLooper());
        }
        return instance;
    }

    public void get(String url, HTTPIntListener listener) {
        String auth = "";
        if (UserLoginStateManager.getInstance().getUser() != null) {
            auth = "Bearer " + UserLoginStateManager.getInstance().getUser().getToken();
        }
        doCall(new OkHttpClient().newBuilder().connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                        .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                        .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS).build(),
                new Builder().url(url).addHeader("Authorization", auth)
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json").build(), listener);
    }

    public void post(String url, Map<String, Object> aParams, HTTPIntListener listener) {
        JSONObject jsonObj = new JSONObject(aParams);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObj.toString());
        Log.d("json content", "json content: " + jsonObj.toString());
        String auth = "";
        if (UserLoginStateManager.getInstance().getUser() != null) {
            auth = "Bearer " + UserLoginStateManager.getInstance().getUser().getToken();
        } else if (UserLoginStateManager.getInstance().getTempToken() != null) {
            auth = "Bearer " + UserLoginStateManager.getInstance().getTempToken();
        }
        doCall(new OkHttpClient().newBuilder().connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS).build(),
                new Builder().url(url).post(body).addHeader("Authorization", auth)
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json").build(), listener);
    }

    public void patch(String url, Map<String, Object> aParams, HTTPIntListener listener) {
        JSONObject jsonObj = new JSONObject(aParams);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObj.toString());
        Log.d("json content", "json content: " + jsonObj.toString());
        String auth = "";
        if (UserLoginStateManager.getInstance().getUser() != null) {
            auth = "Bearer " + UserLoginStateManager.getInstance().getUser().getToken();
        }
        doCall(new OkHttpClient().newBuilder().connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS).build(), new Builder().url(url).patch(body).addHeader("Authorization", auth).addHeader("Accept", "application/json").addHeader("Content-Type", "application/json").build(), listener);
    }

    private void doCall(OkHttpClient client, Request request, final HTTPIntListener listener) {
        if (listener != null) {
            listener.onStart();
        }
        client.newCall(request).enqueue(new Callback() {

            public void onFailure(Call call, IOException e) {
                if (listener != null) {
                    HTTPIntConnection.mainHandler.post(new Runnable() {
                        public void run() {
                            listener.onError(APIError.ERROR_CONNECTION);
                        }
                    });
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                BufferedReader in = new BufferedReader(new InputStreamReader(response.body().byteStream()));
                StringBuilder bodyBuilder = new StringBuilder();
                while (true) {
                    String line = in.readLine();
                    if (line == null) {
                        break;
                    }
                    bodyBuilder.append(line);
                }
                final String body = bodyBuilder.toString();
                final int code = response.code();
                Log.d("response", "response: of: " + call.request().url() + " code: " + code + ", body: " + body);
                if (listener != null) {
                    HTTPIntConnection.mainHandler.post(new Runnable() {
                        public void run() {
                            if (code == ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION || code == 204 || code == 201) {
                                listener.onSuccess(body);
                            } else if (code == 401) {
                                listener.onError(APIError.SESSION_EXPIRED);
                            } else if (code == 409) {
                                listener.onError(APIError.ERROR_CONFLICT);
                            } else if (code == 403) {
                                listener.onError(APIError.ERROR_FORBIDDEN);
                            } else if (code == 404) {
                                listener.onError(APIError.ERROR_NOT_FOUND);
                            } else {
                                listener.onError(APIError.INVALID_RESPONSE);
                            }
                        }
                    });
                }
            }
        });
    }
}
