package com.example.applicationv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LGJeu extends AppCompatActivity {

    ArrayList<String> ordres = new ArrayList<>(Arrays.asList("Voleur", "Cupidon", "Voyante", "Loup-Garou", "Sorcière"));
    ImageView soleilLune;
    TextView topText, midText, bottomText, titleText;
    ImageButton prevButton, nextButton;
    ConstraintLayout layout;
    ArrayList<TextView> textViews = new ArrayList<>();

    int txt;
    int roleAct;
    int nbVictimes = 0;

    HashMap<String, String> roles = new HashMap<>();

    ArrayList<String> nuit = new ArrayList<>(Arrays.asList("Le village s'endort."));
    ArrayList<String> voleur = new ArrayList<>(Arrays.asList("Le voleur se réveille.", "Il choisit une carte à échanger avec la sienne...", "Le voleur se rendort."));
    ArrayList<String> cupidon = new ArrayList<>(Arrays.asList("Cupidon se réveille.", "Il choisit deux joueurs qui vont tomber amoureux...", "Cupidon se rendort.",
            "Les amoureux dont je vais toucher la tête se réveillent et se reconnaissent.", "Les amoureux se rendorment."));
    ArrayList<String> voyante = new ArrayList<>(Arrays.asList("La voyante se réveille.", "Elle choisit une personne dont elle souhaite connaître le rôle...", "La voyante se rendort."));
    ArrayList<String> loups = new ArrayList<>(Arrays.asList("Les loup-garous se réveillent.", "Ils choisissent une personne à dévorer...", "Les loups se rendorment."));
    ArrayList<String> sorciere = new ArrayList<>(Arrays.asList("La sorcière se réveille",
            "Je lui montre la victime des Loups-Garous. Va-t-elle user de sa potion de guérison, ou d’empoisonnement ?", "La sorcière se rendort."));
    ArrayList<String> jour = new ArrayList<>(Arrays.asList("Le village se réveille.", "Il y a eu " + String.valueOf(nbVictimes) + " vitcimes cette nuit.", "Le village va donc voter pour éliminer quelqu'un."));

    ArrayList<ArrayList<String>> ordre = new ArrayList<>(Arrays.asList(nuit, voleur, cupidon, voyante, loups, sorciere, jour));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_g_jeu);

        layout = findViewById(R.id.LGLayout);
        soleilLune = findViewById(R.id.jourNuitImageView);
        topText = findViewById(R.id.topTextView);
        midText = findViewById(R.id.midTextView);
        bottomText = findViewById(R.id.bottomTextView);
        titleText = findViewById(R.id.LGTitleTextView);
        prevButton = findViewById(R.id.prevImageButton);
        nextButton = findViewById(R.id.nextImageButton);
        textViews.add(topText);
        textViews.add(midText);
        textViews.add(bottomText);

        roles = (HashMap<String, String>) getIntent().getExtras().get("roles");

        roleAct = 0;
        txt = 0;

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViews.get(txt % 3).setText(ordre.get(roleAct).get(txt));
                if (roleAct == 0 && txt == 0) {
                    soleilLune.setImageResource(R.drawable.lune);
                    layout.setBackgroundColor(getResources().getColor(R.color.black));
                    topText.setTextColor(getResources().getColor(R.color.white));
                    midText.setTextColor(getResources().getColor(R.color.white));
                    bottomText.setTextColor(getResources().getColor(R.color.white));
                    titleText.setTextColor(getResources().getColor(R.color.white));
                } else if (roleAct == ordre.size() - 1 && txt == 0) {
                    soleilLune.setImageResource(R.drawable.soleil);
                    layout.setBackgroundColor(getResources().getColor(R.color.teal_200));
                    topText.setTextColor(getResources().getColor(R.color.black));
                    midText.setTextColor(getResources().getColor(R.color.black));
                    bottomText.setTextColor(getResources().getColor(R.color.black));
                    titleText.setTextColor(getResources().getColor(R.color.black));
                }
                if (txt % 3 == 0) {
                    textViews.get(1).setText("");
                    textViews.get(2).setText("");
                }
                if (txt < ordre.get(roleAct).size() - 1) {
                    txt++;
                } else if (roleAct < ordre.size() - 1) {
                    if (ordre.get(roleAct).equals(voleur) || ordre.get(roleAct).equals(cupidon)) {
                        ordre.remove(roleAct);
                    } else {
                        roleAct++;
                    }
                    txt = 0;
                } else {
                    roleAct = 0;
                    txt = 0;
                }
            }
        });
    }
}