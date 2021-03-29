package com.example.applicationv3;

import android.app.AppOpsManager;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class JoueursAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<String> Noms = new ArrayList<>();
    ArrayList<String> SAM = new ArrayList<>();
    ArrayList<Integer> images = new ArrayList<>();


    public JoueursAdapter(Context c, ArrayList<String> n, ArrayList<String> s, ArrayList<Integer> i){
        Noms = n;
        SAM = s;
        images = i;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return Noms.size();
    }

    @Override
    public Object getItem(int position) {
        return Noms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.listview_joueurs, null);

        EditText NomJoueurEditText = (EditText) view.findViewById(R.id.NomJoueurEditText);
        CheckBox SAMCheckBox = (CheckBox) view.findViewById(R.id.SAMCheckBox);
        ImageView JoueurImageView = (ImageView) view.findViewById(R.id.JoueurImageView);



        NomJoueurEditText.setHint(Noms.get(position));
        SAMCheckBox.setText(SAM.get(position));
        JoueurImageView.setImageResource(images.get(position));
        return view;
    }
}
