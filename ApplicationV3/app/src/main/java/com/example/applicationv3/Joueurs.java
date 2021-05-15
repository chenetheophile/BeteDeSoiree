package com.example.applicationv3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Joueurs extends AppCompatActivity {


    public static int nbJoueurs;

    ArrayList<Integer> images = new ArrayList<>();
    ArrayList<String> joueurs = new ArrayList<>();

    int imageList[] = {R.drawable.happy, R.drawable.angry, R.drawable.shocked, R.drawable.cool, R.drawable.bored};
    int minJoueurs = 2;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter joueursAdapter;
    public static HashMap<Integer, String> nomsJoueurs = new HashMap<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joueurs);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        int IdJeu = getIntent().getExtras().getInt("IdJeu");
        minJoueurs = getResources().getIntArray(R.array.min_joueurs)[IdJeu];
        if (nbJoueurs < minJoueurs) {
            nbJoueurs = minJoueurs;
        }
        for (int i = 0; i < minJoueurs; i++) {
            images.add(imageList[new Random().nextInt(5)]);
        }

        joueursAdapter = new JoueursAdapter(this, images);

        FloatingActionButton AddButton = (FloatingActionButton) findViewById(R.id.AddActionButton);
        FloatingActionButton RemoveButton = (FloatingActionButton) findViewById(R.id.RemoveActionButton);
        FloatingActionButton SendButton = (FloatingActionButton) findViewById(R.id.SendActionButton);

        RecyclerView ListeJoueurs = (RecyclerView) findViewById(R.id.ListeJoueurs);
        ListeJoueurs.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        ListeJoueurs.setLayoutManager(layoutManager);
        ListeJoueurs.setAdapter(joueursAdapter);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nbJoueurs < 18) {
                    nbJoueurs++;
                    images.add(imageList[new Random().nextInt(5)]);
                    joueursAdapter.notifyDataSetChanged();
                }
            }
        });

        RemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nbJoueurs > minJoueurs) {
                    nbJoueurs--;
                    images.remove(images.size() - 1);
                    joueursAdapter.notifyDataSetChanged();
                }
            }
        });

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < nomsJoueurs.size(); i++) {
                    if (joueurs.contains(nomsJoueurs.get(i))) {
                        int a = 2;
                        while (joueurs.contains(nomsJoueurs.get(i) + String.valueOf(a))){
                            a++;
                        }
                        joueurs.add(nomsJoueurs.get(i) + String.valueOf(a));
                    } else {
                        joueurs.add(nomsJoueurs.get(i));
                    }
                }
                switch (IdJeu) {
                    case (0):
                        Intent startIntent = new Intent(getApplicationContext(), ActionVerite.class);
                        startIntent.putExtra("IdJeu", IdJeu);
                        startIntent.putExtra("Joueurs", joueurs);
                        startActivity(startIntent);
                        break;
                    case (1):
                        Snackbar.make(v, "Palmier", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case (2):
                        Intent intent = new Intent(getApplicationContext(), Loup_Garou.class);
                        intent.putExtra("Joueurs", joueurs);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}