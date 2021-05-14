package com.example.applicationv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChoixAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<String> names  = new ArrayList<>();
    private ArrayList<String> description  = new ArrayList<>();
    private ArrayList<Integer> images  = new ArrayList<>();
    private ArrayList<Boolean> choix = new ArrayList<>();


    public ChoixAdapter(Context c, ArrayList<String> n, ArrayList<String> d,ArrayList<Integer> img){
        names = n;
        description = d;
        images = img;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < names.size(); i++) {
            choix.add(false);
        }
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public boolean isChoisi(int position) { return choix.get(position); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.listview_choix, null);
        TextView nomTextView = (TextView) v.findViewById(R.id.nomCarteTextView);
        TextView descTextView = (TextView) v.findViewById(R.id.descCarteTextView);
        ImageView carteImageView = (ImageView) v.findViewById(R.id.cartesImageView);
        CheckBox choixCheckBox = (CheckBox) v.findViewById(R.id.choixCheckBox);

        nomTextView.setText(names.get(position));
        descTextView.setText(description.get(position));
        carteImageView.setImageResource(images.get(position));

        if (choixCheckBox.isChecked()) {
            choix.set(position, true);
        }
        return v;
    }
}
