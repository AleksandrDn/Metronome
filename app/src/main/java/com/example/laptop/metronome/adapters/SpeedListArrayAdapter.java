package com.example.laptop.metronome.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laptop.metronome.R;
import com.example.laptop.metronome.items.Tempo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laptop on 20.02.2015.
 */
public class SpeedListArrayAdapter extends BaseAdapter {

    private List<Tempo> items;
    private String[] tempoNames;
    private Context cont;
    private LayoutInflater lInflater;

    public SpeedListArrayAdapter(Context context, List<Tempo> items, String[] tempoNames) {
        cont = context;
        this.items = items;
        this.tempoNames = tempoNames;
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
            view = lInflater.inflate(R.layout.tempo_lsit_item, parent, false);
        }

        final Tempo t = getTempoItem(position);

        FillView(view, t);
        return view;
    }

    private void FillView (View view, Tempo t)
    {
        ((TextView) view.findViewById(R.id.tempo_name_italian)).setText(t.getName());
        ((TextView) view.findViewById(R.id.tempo_name_local)).setText(tempoNames[t.getId()]);
        ((TextView) view.findViewById(R.id.ticks_per_min)).setText(t.getBottomLevelTick() + " - " + t.getTopLevelTick());

        if (t.isEnabled())
            ((ImageView) view.findViewById(R.id.enable_state_icon)).setImageResource(R.drawable.ic_launcher);
    }

    private Tempo getTempoItem(int position) {
        return ((Tempo) getItem(position));
    }
}
