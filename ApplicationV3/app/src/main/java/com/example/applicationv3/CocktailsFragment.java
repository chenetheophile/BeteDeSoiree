package com.example.applicationv3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
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
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class CocktailsFragment extends Fragment {

    private ListView ListeCocktails;
    private String[] noms_cocktails;
    private String[] detail_cocktails;
    private String[] desc_cocktails;
    private String[] lien;
    int[] images;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cocktails_fragment_layout, container, false);

        //affiche les cocktails selon un modèle qu on adapte et recupere les cocktails etc depuis les documents recuperer de la bdd
        ListeCocktails = rootView.findViewById(R.id.ListeCocktails);
        noms_cocktails = getActivity().getIntent().getExtras().getStringArray("listeNomC");
        desc_cocktails = getActivity().getIntent().getExtras().getStringArray("DescriptionC");
        detail_cocktails = getActivity().getIntent().getExtras().getStringArray("listeIngreC");
        int taille=desc_cocktails.length;
        lien=getActivity().getIntent().getExtras().getStringArray("lienC");
        images=new int[noms_cocktails.length];
        for(int i=0;i<noms_cocktails.length;i++){
            images[i]=R.drawable.ic_launcher_foreground;
        }
        JeuxAdapter jeuxAdapter = new JeuxAdapter(this.getContext(), noms_cocktails, detail_cocktails, desc_cocktails, images);
        ListeCocktails.setAdapter(jeuxAdapter);
        ListeCocktails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override//si on click sur une recette affiche son detail
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent cocktail = new Intent(getActivity(), affichage_Recette.class);
                cocktail.putExtra("Ingredient",detail_cocktails[position] );
                cocktail.putExtra("Nom", noms_cocktails[position]);
                cocktail.putExtra("lien", lien[position]);

                getActivity().startActivity(cocktail);
            }
        });
        FloatingActionButton ajout=rootView.findViewById(R.id.ajoutCocktail);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proposition=new Intent(getContext(), Proposition.class);
                proposition.putExtra("type","Cocktail");
                proposition.putExtra("User",(FirebaseUser)getActivity().getIntent().getExtras().get("User"));
                startActivity(proposition);
            }
        });
        return rootView;
    }
}
