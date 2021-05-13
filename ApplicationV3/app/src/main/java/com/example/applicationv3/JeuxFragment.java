package com.example.applicationv3;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class JeuxFragment extends Fragment {

    private RecyclerView ListeJeux;
    private String[] noms_jeux;
    private String[] joueurs_jeux;
    private String[] equipement_jeux;
    int images[] = {R.drawable.action_verite, R.drawable.palmier, R.drawable.loup_garou};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.jeux_fragment_layout, container, false);
        ListeJeux = (RecyclerView) rootView.findViewById(R.id.ListeJeux);
//        noms_jeux = getResources().getStringArray(R.array.noms_jeux);
//        joueurs_jeux = getResources().getStringArray(R.array.joueurs_jeux);
//        equipement_jeux = getResources().getStringArray(R.array.equipement_jeux);
//
        JeuxAdapter jeuxAdapter = new JeuxAdapter(this.getContext(), noms_jeux, joueurs_jeux, equipement_jeux, images);
        ListeJeux.setAdapter(jeuxAdapter);
        ListeJeux.setLayoutManager(new LinearLayoutManager(getContext()));
//        ListeJeux.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent startIntent = new Intent(getActivity(), Joueurs.class);
//                startIntent.putExtra("IdJeu", position);
//                startActivity(startIntent);
//            }
//        });
        return rootView;
    }
}
