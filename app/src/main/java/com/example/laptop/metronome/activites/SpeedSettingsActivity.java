package com.example.laptop.metronome.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.laptop.metronome.R;
import com.example.laptop.metronome.adapters.SpeedListArrayAdapter;

/**
 * Created by laptop on 05.02.2015.
 */
public class SpeedSettingsActivity extends Activity {

    private ListView temposList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speed_settings_tab);

        SpeedListArrayAdapter adapter = new SpeedListArrayAdapter(this, MainActivity.getTemps(), MainActivity.getTempsNamesLocal());
        temposList = (ListView) findViewById(R.id.tempos_list_view);
        temposList.setAdapter(adapter);

        setListener();


    }

    private void setListener() {
        temposList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent returnIntent = new Intent();
                Log.d("Selected", position+"");
                returnIntent.putExtra("TEMPO_ID", position);
                setResult(RESULT_OK, returnIntent);
                finish();

            }
        });
    }
}
