package com.example.laptop.metronome.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.laptop.metronome.R;
import com.example.laptop.metronome.adapters.SoundListArrayAdapter;

/**
 * Created by laptop on 16.02.2015.
 */
public class SoundSettingsActivity extends Activity {

    private ListView soundsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_settings_tab);

        SoundListArrayAdapter adapter = new SoundListArrayAdapter(this, MainActivity.getSounds(), MainActivity.getSoundsNamesLocal());
        soundsList = (ListView) findViewById(R.id.sounds_list_view);

        soundsList.setAdapter(adapter);

        setListener();
    }
    private void setListener() {
        soundsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent();
                Log.d("Selected", position + "");
                intent.putExtra("SOUND_ID", position);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}
