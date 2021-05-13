package com.example.applicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CocktailsFragment extends Fragment {

    private RecyclerView ListeCocktails;
    private String[] noms_cocktails;
    private String[] detail_cocktails;
    private String[] desc_cocktails;
    private String[] lien_img;
    int[] images;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cocktails_fragment_layout, container, false);

        //affiche les cocktails selon un modèle qu on adapte et recupere les cocktails etc depuis les documents recuperer de la bdd
        ListeCocktails = rootView.findViewById(R.id.ListeCocktails);

        ArrayList<Recette>listeRecette= (ArrayList<Recette>) getActivity().getIntent().getExtras().get("Cocktail");

        ArrayList<String>nom=new ArrayList<>(),desc=new ArrayList<>(),ingr=new ArrayList<>(),lien=new ArrayList<>();

        for (int i=0;i<listeRecette.size();i++){
            Recette tempo=listeRecette.get(i);
            nom.add(tempo.getNom());
            desc.add(tempo.getDescription());
            ingr.add(tempo.getIngredient());
            lien.add(tempo.getLien());
        }
        noms_cocktails=nom.toArray(new String[nom.size()]);
        desc_cocktails=desc.toArray(new String[desc.size()]);
        detail_cocktails=ingr.toArray(new String[ingr.size()]);
        lien_img= lien.toArray(new String[lien.size()]);

        images=new int[noms_cocktails.length];
        for(int i=0;i<noms_cocktails.length;i++){
            images[i]=R.drawable.ic_launcher_foreground;
        }
        JeuxAdapter jeuxAdapter = new JeuxAdapter(this.getContext(), noms_cocktails, detail_cocktails, desc_cocktails, images);
        ListeCocktails.setAdapter(jeuxAdapter);
        ListeCocktails.setLayoutManager(new LinearLayoutManager(getContext()));
//        ListeCocktails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override//si on click sur une recette affiche son detail
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent cocktail = new Intent(getActivity(), affichage_Recette.class);
//                cocktail.putExtra("Ingredient",detail_cocktails[position] );
//                cocktail.putExtra("Nom", noms_cocktails[position]);
//                cocktail.putExtra("lien", lien_img[position]);
//
//                getActivity().startActivity(cocktail);
//            }
//        });
//        FloatingActionButton ajout=rootView.findViewById(R.id.ajoutCocktail);
//        ajout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent proposition=new Intent(getContext(), Proposition.class);
//                proposition.putExtra("type","Cocktail");
//                proposition.putExtra("User",(FirebaseUser)getActivity().getIntent().getExtras().get("User"));
//                startActivity(proposition);
//            }
//        });
//        EditText recherche=rootView.findViewById(R.id.barreRecherche);
//        recherche.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                jeuxAdapter.getFilter().filter(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        return rootView;
    }
}
