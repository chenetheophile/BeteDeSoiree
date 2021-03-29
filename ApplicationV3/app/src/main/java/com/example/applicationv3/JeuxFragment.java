package com.example.applicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class JeuxFragment extends Fragment {

    private ListView ListeJeux;
    private String[] noms_jeux;
    private String[] joueurs_jeux;
    private String[] equipement_jeux;
    int images[] = {R.drawable.action_verite, R.drawable.palmier, R.drawable.loup_garou};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.jeux_fragment_layout, container, false);
        ListeJeux = (ListView) rootView.findViewById(R.id.ListeJeux);
        noms_jeux = getResources().getStringArray(R.array.noms_jeux);
        joueurs_jeux = getResources().getStringArray(R.array.joueurs_jeux);
        equipement_jeux = getResources().getStringArray(R.array.equipement_jeux);

        JeuxAdapter jeuxAdapter = new JeuxAdapter(this.getContext(), noms_jeux, joueurs_jeux, equipement_jeux, images);
        ListeJeux.setAdapter(jeuxAdapter);

        ListeJeux.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent startIntent = new Intent(getActivity(), Joueurs.class);
                startIntent.putExtra("IdJeu", position);
                startActivity(startIntent);
            }
        });
        return rootView;
    }
}
