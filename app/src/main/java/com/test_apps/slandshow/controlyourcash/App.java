package com.test_apps.slandshow.controlyourcash;

import android.app.Application;

import com.balram.locker.view.AppLocker;

/**
 * Created by Admin on 14.02.2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppLocker.getInstance().enableAppLock(this);
    }
}
