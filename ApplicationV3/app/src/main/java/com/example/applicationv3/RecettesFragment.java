package com.example.applicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RecettesFragment extends Fragment {

    private RecyclerView ListeRecettes;
    private String[] noms_recettes;
    private String[] detail_recettes;
    private String[] desc_recettes;
    private String[] lien_img;
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
//        ListeRecettes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent recipe= new Intent(getActivity(), affichage_Recette.class);
//
//                recipe.putExtra("Nom",noms_recettes[position]);
//                recipe.putExtra("Ingredient",detail_recettes[position]);
//                recipe.putExtra("lien",lien_img[position]);
//
//                getActivity().startActivity(recipe);
//
//            }
//        });
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
