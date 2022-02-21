package com.example.applicationv3.Jeux;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.applicationv3.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PalmierJeu extends AppCompatActivity {

    private ArrayList<Integer> images;
    private ArrayList<String> desc;
    private RecyclerView listeCartes;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        images = new ArrayList<>();
        desc = new ArrayList<>();
        Collections.addAll(desc, getResources().getStringArray(R.array.regles_palmier));
        for (int i = 0; i < 14; i++) {
            images.add(R.drawable.ptinterrogation);
        }
        setContentView(R.layout.activity_palmier_jeu);
        listeCartes = findViewById(R.id.listeCartesPalmier);
        adapter = new CartePalmierAdapter(this, images, desc);
        layoutManager = new LinearLayoutManager(this);
        listeCartes.setAdapter(adapter);
        listeCartes.setLayoutManager(layoutManager);
    }
}