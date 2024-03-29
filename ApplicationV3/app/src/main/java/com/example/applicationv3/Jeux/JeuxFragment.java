package com.example.applicationv3.Jeux;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationv3.R;
import com.example.applicationv3.SansCategorie.Item;

import java.util.ArrayList;
import java.util.Arrays;


public class JeuxFragment extends Fragment {

    private EditText barre;
    private RecyclerView ListeJeux;
    private ArrayList<Item> jeux =new ArrayList<>();

    ArrayList<Integer> images=new ArrayList<>(Arrays.asList(R.drawable.action_verite, R.drawable.palmier, R.drawable.loup_garou));

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.jeux_fragment_layout, container, false);
        ListeJeux = (RecyclerView) rootView.findViewById(R.id.ListeJeux);

        String[] noms_jeux = getResources().getStringArray(R.array.noms_jeux);
        String[] joueurs_jeux = getResources().getStringArray(R.array.joueurs_jeux);
        String[] equipement_jeux = getResources().getStringArray(R.array.equipement_jeux);
        if (jeux.size()!=noms_jeux.length){
            for(int i=0;i<noms_jeux.length;i++){
                jeux.add(new Item("Jeux",noms_jeux[i],joueurs_jeux[i],equipement_jeux[i],true));
            }}

        JeuxAdapter jeuxAdapter = new JeuxAdapter(this.getContext(), jeux, images);
        ListeJeux.setAdapter(jeuxAdapter);
        ListeJeux.setLayoutManager(new LinearLayoutManager(getContext()));

        barre=getActivity().findViewById(R.id.barreRecherche);
        ImageButton rechercher=getActivity().findViewById(R.id.boutonrecherche);
        rechercher.setOnClickListener(v -> {

            if (barre.getVisibility()==View.INVISIBLE){
                barre.setVisibility(View.VISIBLE);
            }else {
                barre.setVisibility(View.INVISIBLE);
            }
        });
        return rootView;
    }
}
