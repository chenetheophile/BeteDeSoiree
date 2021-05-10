package com.example.applicationv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter joueursAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joueurs);

        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        int IdJeu = getIntent().getExtras().getInt("IdJeu");
        minJoueurs = getResources().getIntArray(R.array.min_joueurs)[IdJeu];
        nbJoueurs = minJoueurs;

        for (int i = 0; i < minJoueurs; i++) {
            Noms.add("Nom j" + String.valueOf(i + 1));
            SAM.add("SAM");
            images.add(imageList[new Random().nextInt(5)]);
        }

        joueursAdapter = new JoueursAdapter(this, Noms, SAM, images);

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
                nbJoueurs++;
                Noms.add("Nom j" + String.valueOf(Noms.size() + 1));
                SAM.add("SAM");
                images.add(imageList[new Random().nextInt(5)]);
                joueursAdapter.notifyDataSetChanged();
                Snackbar.make(v, "+1", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                    Snackbar.make(v, "-1", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (IdJeu) {
                    case (0):
                        Intent startIntent = new Intent(getApplicationContext(), ActionVerite.class);
                        startIntent.putExtra("IdJeu", IdJeu);
                        startActivity(startIntent);
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
    }
}