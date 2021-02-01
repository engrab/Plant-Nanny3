package com.example.plantnany;


import android.media.MediaPlayer;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class App extends MultiDexApplication {




    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(getApplicationContext());


    }


}
