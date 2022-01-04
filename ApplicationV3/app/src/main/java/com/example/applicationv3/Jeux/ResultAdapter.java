package com.example.applicationv3.Jeux;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.applicationv3.R;

import java.util.ArrayList;

public class ResultAdapter extends BaseAdapter {

    ArrayList<String> joueurs = new ArrayList<>();
    ArrayList<Integer> rep = new ArrayList<>();
    int total;
    LayoutInflater mInflater;

    public ResultAdapter(Context c, ArrayList<String> j, ArrayList<Integer> r, int t) {
        joueurs = j;
        rep = r;
        total = t;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return joueurs.size();
    }

    @Override
    public Object getItem(int position) {
        return rep.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.listview_resultat, null);
        TextView joueurTextView = v.findViewById(R.id.joueurTextView);
        TextView scoreTextView = v.findViewById(R.id.scoreTextView);

        String nom = joueurs.get(position);
        String score = rep.get(position) + "/" + String.valueOf(total);

        joueurTextView.setText(nom);
        scoreTextView.setText(score);
        return v;
    }
}
