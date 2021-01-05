package com.example.plantnany;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class Application extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(getApplicationContext());
    }
}
