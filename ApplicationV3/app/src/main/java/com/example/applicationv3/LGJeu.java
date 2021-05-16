package com.example.applicationv3;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LGJeu extends AppCompatActivity {

    ImageView soleilLune;
    TextView topText, midText, bottomText, titleText;
    Button nextButton;
    ConstraintLayout layout;
    ArrayList<TextView> textViews = new ArrayList<>();
    ArrayList<String> listeRoles;
    ArrayList<String> listeJoueurs;
    ArrayList<Integer> listeImages;
    Map<String, Integer> img;

    int txt;
    int roleAct;
    int nbVictimes = 0;

    HashMap<String, String> roles = new HashMap<>();

    ArrayList<String> nuit = new ArrayList<>(Arrays.asList("Le village s'endort."));
    ArrayList<String> voleur = new ArrayList<>(Arrays.asList("Le voleur se réveille.", "Il choisit une carte à échanger avec la sienne...", "Le voleur se rendort."));
    ArrayList<String> cupidon = new ArrayList<>(Arrays.asList("Cupidon se réveille.", "Il choisit deux joueurs qui vont tomber amoureux...", "Cupidon se rendort.",
            "Les amoureux dont je vais toucher la tête se réveillent et se reconnaissent.", "Les amoureux se rendorment."));
    ArrayList<String> voyante = new ArrayList<>(Arrays.asList("La voyante se réveille.", "Elle choisit une personne dont elle souhaite connaître le rôle...",
            "La voyante se rendort."));
    ArrayList<String> loups = new ArrayList<>(Arrays.asList("Les loup-garous se réveillent.", "Ils choisissent une personne à dévorer...", "", "Les loups se rendorment."));
    ArrayList<String> sorciere = new ArrayList<>(Arrays.asList("La sorcière se réveille",
            "Je lui montre la victime des Loups-Garous. Va-t-elle user de sa potion de guérison, ou d’empoisonnement ?", "La sorcière se rendort."));
    ArrayList<String> jour = new ArrayList<>(Arrays.asList("Le village se réveille.", "Il y a eu " + String.valueOf(nbVictimes) + " vitcimes cette nuit.",
            "Le village va donc voter pour éliminer quelqu'un.", "Le village se rendort."));

    ArrayList<ArrayList<String>> ordre = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_g_jeu);

        listeRoles = getIntent().getStringArrayListExtra("roles");
        listeJoueurs = getIntent().getStringArrayListExtra("joueurs");
        listeImages = getIntent().getIntegerArrayListExtra("images");

        img = Map.of("Loup-Garou", R.drawable.loupgarou, "Voyante", R.drawable.voyante, "Petite Fille", R.drawable.petitefille, "Cupidon", R.drawable.cupidon,
                "Chasseur", R.drawable.chasseur, "Sorcière", R.drawable.sorciere, "Voleur", R.drawable.voleur, "Villageois", R.drawable.villageois);

        roles = (HashMap<String, String>) getIntent().getExtras().get("rolesMap");
        Set<String> keys = roles.keySet();
        ordre.add(nuit);
        if (keys.contains("Voleur")) {
            ordre.add(voleur);
        }
        if (keys.contains("Cupidon")) {
            ordre.add(cupidon);
        }
        if (keys.contains("Voyante")) {
            ordre.add(voyante);
        }
        if (keys.contains("Loup-Garou")) {
            ordre.add(loups);
        }
        if (keys.contains("Sorcière")) {
            ordre.add(sorciere);
        }
        ordre.add(jour);

        layout = findViewById(R.id.LGLayout);
        soleilLune = findViewById(R.id.jourNuitImageView);
        topText = findViewById(R.id.topTextView);
        midText = findViewById(R.id.midTextView);
        bottomText = findViewById(R.id.bottomTextView);
        titleText = findViewById(R.id.LGTitleTextView);
        nextButton = findViewById(R.id.LGNextButton);
        textViews.add(topText);
        textViews.add(midText);
        textViews.add(bottomText);

        roleAct = 0;
        txt = 0;

        layout.setBackgroundResource(R.drawable.jour);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roleAct == 2 && txt == 2) {

                    Intent intent = new Intent(getApplicationContext(), ChoixLG.class);
                    ArrayList<String> pListeRoles = new ArrayList<>();
                    ArrayList<String> pListeJoueurs = new ArrayList<>();
                    ArrayList<Integer> pListeImages = new ArrayList<>();

                    for (int i = 0; i < listeImages.size(); i++) {
                        if (listeImages.get(i) != R.drawable.loupgarou) {
                            pListeJoueurs.add(listeJoueurs.get(i));
                            pListeRoles.add(listeRoles.get(i));
                            pListeImages.add(listeImages.get(i));
                        }
                    }

                    intent.putExtra("joueurs", pListeJoueurs);
                    intent.putExtra("roles", pListeRoles);
                    intent.putExtra("images", pListeImages);
                    intent.putExtra("role", "Loup-Garou");
                    startActivityForResult(intent, 1);
                } else {
                    textViews.get(txt % 3).setText(ordre.get(roleAct).get(txt));
                    if (roleAct == 0 && txt == 0) {
                        nextButton.setText("Suite");
                        soleilLune.setImageResource(R.drawable.lune);
                        layout.setBackgroundResource(R.drawable.nuit);
                        topText.setTextColor(getResources().getColor(R.color.white));
                        midText.setTextColor(getResources().getColor(R.color.white));
                        bottomText.setTextColor(getResources().getColor(R.color.white));
                        titleText.setTextColor(getResources().getColor(R.color.white));
                    } else if (roleAct == ordre.size() - 1 && txt == 0) {
                        soleilLune.setImageResource(R.drawable.soleil);
                        layout.setBackgroundResource(R.drawable.jour);
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
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("result", -1);
                txt++;
            }
        }
    }
}