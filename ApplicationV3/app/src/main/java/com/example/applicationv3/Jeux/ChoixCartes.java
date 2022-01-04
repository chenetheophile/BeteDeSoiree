package com.example.applicationv3.Jeux;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.applicationv3.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class ChoixCartes extends Fragment {

    private RecyclerView listeCartes;
    public ArrayList<String> nomsCartes = new ArrayList<>();
    public ArrayList<Integer> imagesCartes = new ArrayList<>(Arrays.asList(R.drawable.loupgarou, R.drawable.voyante, R.drawable.petitefille, R.drawable.cupidon, R.drawable.chasseur, R.drawable.sorciere, R.drawable.voleur, R.drawable.villageois));
    public String[] defaut;

    Button jouerButton;
    Button reglesButton;
    Button defautButton;

    RecyclerView.Adapter choixAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choix_cartes, container, false);

        ArrayList<String> joueurs = getActivity().getIntent().getStringArrayListExtra("Joueurs");

        listeCartes = rootView.findViewById(R.id.ListeCartes);
        choixAdapter = new ChoixCarteAdapter(this.getContext(), nomsCartes, imagesCartes);

        defaut = getResources().getStringArray(R.array.repartition);
        String[] nameArray = getResources().getStringArray(R.array.cartes_lg);
        for (int i = 0; i < nameArray.length; i++) {
            nomsCartes.add(nameArray[i]);
        }

        jouerButton = rootView.findViewById(R.id.JouerButtonLG);
        jouerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nbCartes = 0;
                for (int i = 0; i < ChoixCarteAdapter.numCartes.keySet().toArray().length; i++) {
                    nbCartes += ChoixCarteAdapter.numCartes.get((String) ChoixCarteAdapter.numCartes.keySet().toArray()[i]);
                }
                if (nbCartes == Joueurs.nbJoueurs - 1) {
                    Intent intent = new Intent(getActivity(), AttributionLG.class);
                    intent.putExtra("Joueurs", joueurs);
                    startActivity(intent);
                } else {
                    Snackbar.make(v, "Le nombre de cartes ne correspond pas au nombre de joueurs.", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        reglesButton = rootView.findViewById(R.id.ReglesButtonLG);
        reglesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getActivity(), ReglesJeu.class);
                startIntent.putExtra("IdJeu", 2);
                startActivity(startIntent);
            }
        });
        layoutManager = new LinearLayoutManager(this.getContext());

        defautButton = rootView.findViewById(R.id.defautButtonLG);
        defautButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < nomsCartes.size(); i++) {
                    ChoixCarteAdapter.numCartes.put(nomsCartes.get(i), Integer.parseInt(String.valueOf(defaut[Joueurs.nbJoueurs - 6].charAt(i))));
                    choixAdapter.notifyDataSetChanged();
                }
            }
        });

        listeCartes.setLayoutManager(layoutManager);
        listeCartes.setAdapter(choixAdapter);
        return rootView;
    }
}