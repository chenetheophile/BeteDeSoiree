package com.example.applicationv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class Joueurs extends AppCompatActivity {


    public static int nbJoueurs;

    ArrayList<String> Noms = new ArrayList<>();
    ArrayList<String> SAM = new ArrayList<>();
    ArrayList<Integer> images = new ArrayList<>();
    int imageList[] = {R.drawable.happy, R.drawable.angry, R.drawable.shocked, R.drawable.cool, R.drawable.bored};
    int minJoueurs = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joueurs);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        JoueursAdapter joueursAdapter = new JoueursAdapter(this, Noms, SAM, images);

        int IdJeu = getIntent().getExtras().getInt("IdJeu");
        minJoueurs = getResources().getIntArray(R.array.min_joueurs)[IdJeu];


        nbJoueurs = minJoueurs;

        for (int i = 0; i < minJoueurs; i++) {
            Noms.add("Nom j" + String.valueOf(i + 1));
            SAM.add("SAM");
            images.add(imageList[new Random().nextInt(5)]);
        }

        FloatingActionButton AddButton = (FloatingActionButton) findViewById(R.id.AddActionButton);
        FloatingActionButton RemoveButton = (FloatingActionButton) findViewById(R.id.RemoveActionButton);
        FloatingActionButton SendButton = (FloatingActionButton) findViewById(R.id.SendActionButton);
        RecyclerView ListeJoueurs = (RecyclerView) findViewById(R.id.ListeJoueurs);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbJoueurs++;
                Noms.add("Nom j" + String.valueOf(Noms.size() + 1));
                SAM.add("SAM");
                images.add(imageList[new Random().nextInt(5)]);
                joueursAdapter.notifyDataSetChanged();
            }
        });

        RemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nbJoueurs > minJoueurs) {
                    nbJoueurs--;
                    Noms.remove(Noms.size() - 1);
                    SAM.remove(SAM.size() - 1);
                    images.remove(images.size() - 1);
                    joueursAdapter.notifyDataSetChanged();
                }
            }
        });

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (IdJeu) {
                    case (0):
                        Snackbar.make(v, "Action ou vérité", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case (1):
                        Snackbar.make(v, "Palmier", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        break;
                    case (2):
                        startActivity(new Intent(getApplicationContext(), Loup_Garou.class));
                        break;
                }
            }
        });

//        ListeJoueurs.setAdapter(joueursAdapter);
    }
}