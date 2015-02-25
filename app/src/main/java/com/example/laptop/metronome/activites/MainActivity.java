package com.example.laptop.metronome.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.laptop.metronome.R;
import com.example.laptop.metronome.items.Sound;
import com.example.laptop.metronome.items.Tempo;
import com.example.laptop.metronome.SoundService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laptop on 05.02.2015.
 */
public class MainActivity extends Activity {

    private static List<Tempo> temps;
    private static String[] tempsNamesLocal;
    private Tempo currentTempo;
    private int currentTicks;

    private static List<Sound> sounds;

    private static String[] soundsNamesLocal;
    private Sound currentSound;

    private boolean playing = false;
    private Intent serviceIntent;
    final String TICKS_COUNT_FOR_SERVICE = "TICKS_COUNT";
    final String SOUND_NAME_FOR_SERVICE = "SOUND_NAME";
    final int REQUEST_CODE_SOUND = 1;
    final int REQUEST_CODE_TEMPO = 2;

    private TextView vTempoName;
    private TextView vTempoNameDescription;
    private TextView vTempoTicksPerMin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        serviceIntent = new Intent(this, SoundService.class);

        fillData();
        setListeners();
        BindViews();
        RefreshView();
        Log.d("ON_CREATE", "Main Activity on create");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(serviceIntent);
    }

    private void setListeners()
    {
        ((ImageButton) findViewById(R.id.change_speed_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, SpeedSettingsActivity.class);
                startActivityForResult(intent, REQUEST_CODE_TEMPO);
            }});

        ((ImageButton) findViewById(R.id.change_sound_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, SoundSettingsActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SOUND);
                stopSoundService();
            }});

        ((ImageButton) findViewById(R.id.plus_tempo_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (currentTicks > 300) return;
                currentTicks++;
                if ((currentTempo.getTopLevelTick() < currentTicks) && (currentTempo.getId() != temps.size()-1)) {
                    currentTempo.setEnabled(false);
                    currentTempo = temps.get(currentTempo.getId() + 1);
                    currentTempo.setEnabled(true);
                }
                RefreshView();
                restartSoundService();
            }});

        ((ImageButton) findViewById(R.id.minus_tempo_button)).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            if (currentTicks < 6) return;
            currentTicks--;
            if ((currentTempo.getBottomLevelTick() > currentTicks) && (currentTempo.getId() != 0)) {
                currentTempo.setEnabled(false);
                currentTempo = temps.get(currentTempo.getId() - 1);
                currentTempo.setEnabled(true);
            }
            RefreshView();
            restartSoundService();
        }});

        ((ImageButton) findViewById(R.id.play_stop_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (playing)
                    stopSoundService();
                else
                    runSoundService();
            }});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) return;
        int id;
        switch (requestCode) {
            case REQUEST_CODE_SOUND:
                currentSound.setEnabled(false);
                id = data.getIntExtra("SOUND_ID", 0);
                currentSound = sounds.get(id);
                currentSound.setEnabled(true);

                runSoundService();
                Log.d("SELECTED_SOUND", soundsNamesLocal[id]);
                break;
            case REQUEST_CODE_TEMPO:
                currentTempo.setEnabled(false);
                id = data.getIntExtra("TEMPO_ID", 0);
                currentTempo = temps.get(id);
                currentTempo.setEnabled(true);
                currentTicks = (currentTempo.getTopLevelTick() - currentTempo.getBottomLevelTick()) / 2 + currentTempo.getBottomLevelTick();
                RefreshView();
                restartSoundService();

                Log.d("SELECTED_TEMPO", currentTempo.getName());
                break;
        }
    }

    private void RefreshView()
    {
        vTempoName.setText(currentTempo.getName());
        vTempoNameDescription.setText(tempsNamesLocal[currentTempo.getId()]);
        vTempoTicksPerMin.setText(currentTicks + " " + getString(R.string.ticks_per_min_text));
    }

    private void fillData()
    {
        temps = new ArrayList<Tempo>();
        temps.add(new Tempo(0, "Largo", 40, 60));
        temps.add(new Tempo(1, "Larghetto", 60, 66));
        temps.add(new Tempo(2, "Adagio", 66, 76));
        temps.add(new Tempo(3, "Andante", 76, 108));
        temps.add(new Tempo(4, "Moderato", 108, 120));
        temps.add(new Tempo(5, "Allegro", 120, 168));
        temps.add(new Tempo(6, "Presto", 168, 200));
        temps.add(new Tempo(7, "Prestissimo", 200, 208));

        currentTempo = temps.get(3);
        currentTempo.setEnabled(true);

        currentTicks = (currentTempo.getTopLevelTick() - currentTempo.getBottomLevelTick()) / 2 + currentTempo.getBottomLevelTick();

        tempsNamesLocal = getResources().getStringArray(R.array.tempo_names_array);

        soundsNamesLocal = getResources().getStringArray(R.array.sound_names_array);

        sounds = new ArrayList<Sound>();
        sounds.add(new Sound(0, R.raw.classic_c_sharp));
        sounds.add(new Sound(1, R.raw.classic_d));
        sounds.add(new Sound(2, R.raw.click));
        sounds.add(new Sound(3, R.raw.plate));
        sounds.add(new Sound(4, R.raw.electronic));
        sounds.add(new Sound(5, R.raw.high_creak));
        sounds.add(new Sound(6, R.raw.low_creak));

        currentSound = sounds.get(0);
        currentSound.setEnabled(true);
    }

    public static List<Tempo> getTemps() {
        return temps;
    }

    public static String[] getTempsNamesLocal() {
        return tempsNamesLocal;
    }

    public static String[] getSoundsNamesLocal() {
        return soundsNamesLocal;
    }

    public static List<Sound> getSounds() {
        return sounds;
    }

    private void stopSoundService()
    {
        stopService(serviceIntent);
        playing = false;
    }
    private void runSoundService()
    {
        serviceIntent.putExtra(TICKS_COUNT_FOR_SERVICE, currentTicks);
        serviceIntent.putExtra(SOUND_NAME_FOR_SERVICE, currentSound.getResourceId());
        startService(serviceIntent);
        playing = true;
    }

    private void restartSoundService()
    {
        stopSoundService();
        runSoundService();
    }

    private void BindViews ()
    {
        vTempoName = (TextView) findViewById(R.id.speed_name_italian_view);
        vTempoNameDescription = (TextView) findViewById(R.id.speed_name_description_view);
        vTempoTicksPerMin = (TextView) findViewById(R.id.tick_per_min_view);
    }
}
