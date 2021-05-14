package com.example.applicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;

public class CocktailsFragment extends Fragment {

    private RecyclerView ListeCocktails;
    private JeuxAdapter jeuxAdapter;
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

        ArrayList<String>nom=new ArrayList<>(),desc=new ArrayList<>(),ingr=new ArrayList<>(),lien=new ArrayList<>();
        barre=getActivity().findViewById(R.id.barreRecherche);
        ImageButton rechercher=getActivity().findViewById(R.id.boutonrecherche);
        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (barre.getVisibility()==View.GONE||barre.getVisibility()==View.INVISIBLE){
                    barre.setVisibility(View.VISIBLE);
                }else {
                    barre.setVisibility(View.GONE);
                }
            }
        });


        barre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                jeuxAdapter.getFilter().filter(barre.getText().toString().toLowerCase().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        for(int i=0;i<listeRecette.size();i++){
            images.add(R.drawable.ic_launcher_foreground);
        }
        jeuxAdapter = new JeuxAdapter(this.getContext(), listeRecette, images);
        ListeCocktails.setAdapter(jeuxAdapter);
        ListeCocktails.setLayoutManager(new LinearLayoutManager(getContext()));


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
