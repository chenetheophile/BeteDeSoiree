package com.example.applicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class CocktailsFragment extends Fragment {

    private ListView ListeCocktails;
    private String[] noms_cocktails;
    private String[] detail_cocktails;
    private String[] desc_cocktails;
    int[] images = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cocktails_fragment_layout, container, false);



        ListeCocktails = rootView.findViewById(R.id.ListeCocktails);
        noms_cocktails = getActivity().getIntent().getExtras().getStringArray("listeNomC");
        detail_cocktails = getActivity().getIntent().getExtras().getStringArray("listeIngreC");

        desc_cocktails = getActivity().getIntent().getExtras().getStringArray("Description");

        JeuxAdapter jeuxAdapter = new JeuxAdapter(this.getContext(), noms_cocktails, detail_cocktails, desc_cocktails, images);
        ListeCocktails.setAdapter(jeuxAdapter);
        /*Si génération d'erreur ici Regarde dans le logcat en cherchant "test" Retiens le dernier chiffre puis vérifie que le startActivity de BDD (ligne 93 OU 123) soit bien après le Log du dernier chiffre.
        *S'il n'est pas à la bonne place déplace le
        *
        *  L'erreur vient d'un problème de thread que j'arrive pas à résoudre.
        *
        *
        * */
        ListeCocktails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent cocktail = new Intent(getActivity(), affichage_Recette.class);
                cocktail.putExtra("Ingredient",detail_cocktails[position] );
                cocktail.putExtra("Nom", noms_cocktails[position]);
                cocktail.putExtra("lien", getActivity().getIntent().getExtras().getStringArray("lienC")[position]);

                getActivity().startActivity(cocktail);
            }
        });
        FloatingActionButton ajout=rootView.findViewById(R.id.ajoutCocktail);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proposition=new Intent(getContext(), Proposition.class);
                proposition.putExtra("type","Cocktail");
                startActivity(proposition);
            }
        });
        return rootView;
    }
}
