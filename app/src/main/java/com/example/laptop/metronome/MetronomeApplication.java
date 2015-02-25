package com.example.laptop.metronome;

import android.app.Application;
import android.content.res.Configuration;

import com.example.laptop.metronome.items.Sound;
import com.example.laptop.metronome.items.Tempo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laptop on 25.02.2015.
 */

public class MetronomeApplication extends Application {

    private static MetronomeApplication instance;

    public static MetronomeApplication getInstance() {
        return instance;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
