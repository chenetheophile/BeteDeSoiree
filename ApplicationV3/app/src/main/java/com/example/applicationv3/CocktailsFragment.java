package com.example.applicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;

public class CocktailsFragment extends Fragment{

    private RecyclerView ListeCocktails;
    public JeuxAdapter jeuxAdapter;
    private ArrayList<Item>listeRecette=new ArrayList<>();
    private ArrayList<Integer>images=new ArrayList<>();

    private EditText barre;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cocktails_fragment_layout, container, false);

        //affiche les cocktails selon un modèle qu on adapte et recupere les cocktails etc depuis les documents recuperer de la bdd
        ListeCocktails = rootView.findViewById(R.id.ListeCocktails);
        listeRecette= (ArrayList<Item>) getActivity().getIntent().getExtras().get("Cocktail");
        for(int i=0;i<listeRecette.size();i++){
            if(i==0){
                images.clear();
            }
            if (listeRecette.get(i).isAlcool()){
                images.add(R.drawable.avec_alcool);
            }else{
                images.add(R.drawable.sans_alcool);
            }

        }
        jeuxAdapter = new JeuxAdapter(this.getContext(), listeRecette, images);
        ListeCocktails.setAdapter(jeuxAdapter);
        ListeCocktails.setLayoutManager(new LinearLayoutManager(getContext()));
        ((donnee)getActivity().getApplication()).setAdapterCocktail(jeuxAdapter);
        barre=getActivity().findViewById(R.id.barreRecherche);
        ImageButton rechercher=getActivity().findViewById(R.id.boutonrecherche);
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (barre.getVisibility()==View.INVISIBLE){
                    barre.setVisibility(View.VISIBLE);
                }else {
                    barre.setVisibility(View.INVISIBLE);
                }
            }
        });


        barre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Filter filtre=jeuxAdapter.getFilter();
                filtre.filter(barre.getText().toString().toLowerCase().trim());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        FloatingActionButton ajout=rootView.findViewById(R.id.ajoutCocktail);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proposition=new Intent(getContext(), Proposition.class);
                proposition.putExtra("type","Cocktail");
                proposition.putExtra("User", (FirebaseUser) getActivity().getIntent().getExtras().get("User"));
                startActivity(proposition);
            }
        });

        return rootView;
    }



}
