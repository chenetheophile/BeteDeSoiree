package com.example.applicationv3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class JeuxAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    private String[] noms_jeux;
    private String[] joueurs_jeux;
    private String[] equipement_jeux;
    private int[] images_jeux;

    public JeuxAdapter(Context c, String[] n, String[] j, String[] e, int[] i){
        noms_jeux = n;
        joueurs_jeux = j;
        equipement_jeux = e;
        images_jeux = i;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return noms_jeux.length;
    }

    @Override
    public Object getItem(int position) {
        return noms_jeux[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.listview_jeux, null);
        TextView NomJeuTextView = (TextView) v.findViewById(R.id.NomJeuTextView);
        TextView DetailJeuTextView = (TextView) v.findViewById(R.id.DetailJeuTextView);
        TextView DescJeuTextView = (TextView) v.findViewById(R.id.DescJeuTextView);
        ImageView ObjetImageView = (ImageView) v.findViewById(R.id.ObjetImageView);

        String nom = noms_jeux[position];
        String detail = joueurs_jeux[position];
        String desc = equipement_jeux[position];

        NomJeuTextView.setText(nom);
        DetailJeuTextView.setText(detail);
        DescJeuTextView.setText(desc);
        ObjetImageView.setImageResource(images_jeux[position]);
        return v;
    }
}
