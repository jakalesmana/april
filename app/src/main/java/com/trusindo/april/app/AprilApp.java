package com.trusindo.april.app;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.trusindo.april.manager.UserLoginStateManager;

/**
 * Created by jakalesmana on 10/14/17.
 */

public class AprilApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        UserLoginStateManager.getInstance().setup(this);
    }
}
