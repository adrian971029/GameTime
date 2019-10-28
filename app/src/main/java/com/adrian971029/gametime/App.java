package com.adrian971029.gametime;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class App extends Application {

    private static App context;

    public void onCreate() {
        super.onCreate();
        context = this;
        Stetho.initializeWithDefaults(this);
    }

    public static App getInstance() {
        return context;
    }

}
