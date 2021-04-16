package com.example.applicationv3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class ChoixCartes extends Fragment {

    private ListView listeCartes;
    public static ArrayList<String> nomsCartes;
    public static ArrayList<String> descCartes;
    public static ArrayList<Integer> imagesCartes;
    public static ChoixAdapter choixAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choix_cartes, container, false);
        listeCartes = rootView.findViewById(R.id.ListeCartes);
        String[] nameArray = getResources().getStringArray(R.array.cartes_lg);
        String[] descArray = getResources().getStringArray(R.array.desc_cartes);

        nomsCartes = new ArrayList<>();
        descCartes = new ArrayList<>();
        imagesCartes = new ArrayList<>();

        for (int i = 0; i < nameArray.length; i++) {
            nomsCartes.add(nameArray[i]);
            descCartes.add(descArray[i]);
            imagesCartes.add(R.drawable.ic_launcher_background);
        }

        if (LGLocal.extChoisies.get(0) == true){
            nomsCartes.add("blip");
            descCartes.add("bloup");
            imagesCartes.add(R.drawable.ic_launcher_background);
        }

        choixAdapter = new ChoixAdapter(this.getContext(), nomsCartes, descCartes, imagesCartes);
        listeCartes.setAdapter(choixAdapter);
        return rootView;
    }
}