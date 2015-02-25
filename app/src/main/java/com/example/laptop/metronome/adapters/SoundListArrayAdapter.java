package com.example.laptop.metronome.adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laptop.metronome.R;
import com.example.laptop.metronome.activites.SoundSettingsActivity;
import com.example.laptop.metronome.items.Sound;
import com.example.laptop.metronome.items.Tempo;

import java.util.List;

/**
 * Created by laptop on 20.02.2015.
 */
public class SoundListArrayAdapter extends BaseAdapter {

    private List<Sound> items;
    private String[] soundNames;
    private Context cont;
    private LayoutInflater lInflater;

    SoundPool sp = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);

    public SoundListArrayAdapter(Context context, List<Sound> items, String[] soundNames) {
        cont = context;
        this.items = items;
        this.soundNames = soundNames;
        lInflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.sound_lsit_item, parent, false);
        }

        final Sound s = getSoundItem(position);
        view.setFocusable(false);
        FillView(view, s);
        return view;
    }

    private void FillView (View view, Sound s)
    {
        //load owners sound
        final int loaded = sp.load(cont, s.getResourceId(), 1);
        ((TextView) view.findViewById(R.id.sound_name)).setText(soundNames[s.getId()]);

        if (s.isEnabled())
            ((ImageView) view.findViewById(R.id.enable_state_icon)).setImageResource(R.drawable.ic_launcher);

        ImageButton imgBtn = (ImageButton) view.findViewById(R.id.listen_sound_example);
        imgBtn.setFocusable(false);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                sp.play(loaded, 1, 1, 0, 0, 1);

            }});

        Log.d("S " + soundNames[s.getId()], " resourceId " + s.getResourceId());
        Log.d("Loaded" + soundNames[s.getId()], " resourceId " + s.getResourceId());
    }

    private Sound getSoundItem(int position) {
        return ((Sound) getItem(position));
    }
}
