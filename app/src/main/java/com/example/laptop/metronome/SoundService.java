package com.example.laptop.metronome;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by laptop on 23.02.2015.
 */
public class SoundService extends Service {

    final String LOG_TAG = "SoundService logs";
    final String TICKS_COUNT_FOR_SERVICE = "TICKS_COUNT";
    final String SOUND_NAME_FOR_SERVICE = "SOUND_NAME";
    final int MINUTE = 60000;

    String soundName;
    final int MAX_STREAMS = 5;
    SoundPool sp;
    int soundId;

    Timer timer;
    TimerTask tTask;
    private int currentInterval;

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
        timer = new Timer();
        sp = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "onStartCommand");
        currentInterval = MINUTE / intent.getIntExtra(TICKS_COUNT_FOR_SERVICE, 0);
        soundId = intent.getIntExtra(SOUND_NAME_FOR_SERVICE, 0);
        Log.d(LOG_TAG, "Interval " + currentInterval);
        Log.d(LOG_TAG, "Sound ID " + soundId);
        soundId = sp.load(this,soundId, 1);
        shedule();
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        tTask.cancel();
        timer.cancel();
        Log.d(LOG_TAG, "onDestroy");
    }

    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }

    private void shedule() {
        if (tTask != null) tTask.cancel();
        if (currentInterval > 0) {
            tTask = new TimerTask() {
                public void run() {
                    sp.play(soundId, 1, 1, 0, 0, 1);
                }
            };
            timer.schedule(tTask, 0, currentInterval);
        }
    }


}
