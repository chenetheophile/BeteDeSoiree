package com.example.applicationv3;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RecettesFragment extends Fragment {

    private EditText barre;
    private RecyclerView ListeRecettes;
   private ArrayList<Integer>images=new ArrayList<>() ;
//voir cocktail la logique est strictement identique
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recettes_fragment_layout, container, false);
        ListeRecettes=rootView.findViewById(R.id.listeRecette);

        ArrayList<Item> listeRecette= (ArrayList<Item>) getActivity().getIntent().getExtras().get("Recette");


        for(int i=0;i<listeRecette.size();i++){
            images.add(R.drawable.ic_launcher_foreground);
        }
        JeuxAdapter jeuxAdapter = new JeuxAdapter(this.getContext(),listeRecette, images);
        ListeRecettes.setAdapter(jeuxAdapter);
        ListeRecettes.setLayoutManager(new LinearLayoutManager(getContext()));
        ((donnee)getActivity().getApplication()).setAdapterRecette(jeuxAdapter);

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
        FloatingActionButton ajout=rootView.findViewById(R.id.ajoutRecette);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proposition=new Intent(getContext(), Proposition.class);
                proposition.putExtra("type","Recette");
                proposition.putExtra("User",(FirebaseUser)getActivity().getIntent().getExtras().get("User"));
                startActivity(proposition);
            }
        });

        return rootView;
    }

}
