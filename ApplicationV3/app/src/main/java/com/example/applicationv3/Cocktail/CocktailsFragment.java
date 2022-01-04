package com.example.applicationv3.Cocktail;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationv3.R;
import com.example.applicationv3.SansCategorie.Item;
import com.example.applicationv3.SansCategorie.ItemAdapter;
import com.example.applicationv3.SansCategorie.Proposition;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CocktailsFragment extends Fragment{

    private RecyclerView ListeCocktails;
    public ItemAdapter itemAdapter;
    private ArrayList<Item>listeRecetteCocktail=new ArrayList<>();

    private EditText barre;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cocktails_fragment_layout, container, false);

        //affiche les cocktails
        ListeCocktails = rootView.findViewById(R.id.ListeCocktails);
        listeRecetteCocktail= (ArrayList<Item>) getActivity().getIntent().getExtras().get("Cocktail");
        itemAdapter = new ItemAdapter(this.getContext(), listeRecetteCocktail);
        ListeCocktails.setAdapter(itemAdapter);
        ListeCocktails.setLayoutManager(new LinearLayoutManager(getContext()));

        //affiche la barre de recherche
        barre=getActivity().findViewById(R.id.barreRecherche);
        ImageButton rechercher=getActivity().findViewById(R.id.boutonrecherche);
        rechercher.setOnClickListener(v -> {

            if (barre.getVisibility()==View.INVISIBLE){
                barre.setVisibility(View.VISIBLE);
            }else {
                barre.setVisibility(View.INVISIBLE);
            }
        });

        //permet de filtrer les resultats
        barre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Filter filtre= itemAdapter.getFilter();
                filtre.filter(barre.getText().toString().toLowerCase().trim());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //ouvre la proposition de recette
        FloatingActionButton ajout=rootView.findViewById(R.id.ajoutCocktail);
        ajout.setOnClickListener(v -> {
            Intent proposition=new Intent(getContext(), Proposition.class);
            proposition.putExtra("type","Cocktail");
            startActivity(proposition);
        });

        return rootView;
    }



}
