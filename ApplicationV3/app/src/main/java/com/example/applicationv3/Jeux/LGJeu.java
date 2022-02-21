package com.example.applicationv3.Jeux;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.applicationv3.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LGJeu extends AppCompatActivity {

    ImageView soleilLune, imageVoyante;
    TextView topText, midText, bottomText, titleText;
    Button nextButton;
    ConstraintLayout layout;
    ArrayList<TextView> textViews = new ArrayList<>();
    ArrayList<String> listeRoles;
    ArrayList<String> listeJoueurs;
    ArrayList<Integer> listeImages;
    ArrayList<Integer> victimes;
    ArrayList<Integer> potions;
    Map<String, Integer> img;

    ActivityResultLauncher<Intent> Resultat;
    int choixVoyante = -1;
    int txt;
    int roleAct;
    int nbVictimes = 0;

    HashMap<String, String> roles = new HashMap<>();

    ArrayList<String> nuit = new ArrayList<>(Collections.singletonList("Le village s'endort."));
    ArrayList<String> voleur = new ArrayList<>(Arrays.asList("Le voleur se réveille.", "Il choisit une carte à échanger avec la sienne...", "Le voleur se rendort."));
    ArrayList<String> cupidon = new ArrayList<>(Arrays.asList("Cupidon se réveille.", "Il choisit deux joueurs qui vont tomber amoureux...", "Cupidon se rendort.",
            "Les amoureux dont je vais toucher la tête se réveillent et se reconnaissent.", "Les amoureux se rendorment."));
    ArrayList<String> voyante = new ArrayList<>(Arrays.asList("La voyante se réveille.", "Elle choisit une personne dont elle souhaite connaître le rôle...",
            "", "La voyante se rendort."));
    ArrayList<String> loups = new ArrayList<>(Arrays.asList("Les loup-garous se réveillent.", "Ils choisissent une personne à dévorer...", "", "Les loups se rendorment."));
    ArrayList<String> sorciere = new ArrayList<>(Arrays.asList("La sorcière se réveille",
            "Je lui montre la victime des Loups-Garous. Va-t-elle user de sa potion de guérison, ou d’empoisonnement ?", "", "La sorcière se rendort."));
    ArrayList<String> jour = new ArrayList<>(Arrays.asList("Le village se réveille.", "Il y a eu ", "",
            "Le village va donc voter pour éliminer quelqu'un."));

    ArrayList<ArrayList<String>> ordre = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_g_jeu);

        Resultat = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data = result.getData();
                    if (result.getResultCode() == RESULT_OK) {
                        switch (data.getStringExtra("role")) {
                            case ("Loup-Garou"):
                                victimes.set(0, listeJoueurs.indexOf(data.getStringExtra("result")));
                                break;
                            case ("Voyante"):
                                choixVoyante = listeJoueurs.indexOf(data.getStringExtra("result"));
                                imageVoyante.setImageResource(listeImages.get(choixVoyante));
                                imageVoyante.setVisibility(View.VISIBLE);
                                titleText.setText(listeRoles.get(listeImages.indexOf(listeImages.get(choixVoyante))));
                                topText.setText("");
                                midText.setText("");
                                break;
                            case ("Sorcière"):
                                if (data.getIntExtra("result", -1) == 1) {
                                    victimes.set(0, -1);
                                }
                            default:
                                break;
                        }
                    }
                }


        );

        victimes = new ArrayList<>(Arrays.asList(-1, -1));

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
            potions = new ArrayList<>(Arrays.asList(1, 1));
        }
        ordre.add(jour);

        layout = findViewById(R.id.LGLayout);
        soleilLune = findViewById(R.id.jourNuitImageView);
        imageVoyante = findViewById(R.id.voyanteImageView);
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

        nextButton.setOnClickListener(v -> {
            if (ordre.get(roleAct).equals(voyante) && txt == 2) {
                appelVoyante();
                txt++;
            } else if (ordre.get(roleAct).equals(loups) && txt == 2) {
                appelLoup();
                txt++;
            } else if (ordre.get(roleAct).equals(sorciere) && txt == 2) {
                appelSorciere();
                txt++;
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
                } else if (roleAct == ordre.size() - 1) {
                    if (txt == 0) {
                        soleilLune.setImageResource(R.drawable.soleil);
                        layout.setBackgroundResource(R.drawable.jour);
                        topText.setTextColor(getResources().getColor(R.color.black));
                        midText.setTextColor(getResources().getColor(R.color.black));
                        bottomText.setTextColor(getResources().getColor(R.color.black));
                        titleText.setTextColor(getResources().getColor(R.color.black));
                    } else if (txt == 1) {
                        nbVictimes = 0;
                        if (victimes.get(0) >= 0) {
                            nbVictimes++;
                        }
                        if (victimes.get(1) >= 0) {
                            nbVictimes++;
                        }
                        textViews.get(txt % 3).setText(ordre.get(roleAct).get(txt) + nbVictimes + " victimes cette nuit.");
                    } else if (txt == 2) {
                        if (nbVictimes > 0) {
                            String text = "Cette nuit, nous avons perdu : ";
                            for (int i = 0; i < nbVictimes; i++) {
                                text += listeJoueurs.get(victimes.get(i)) + ", qui était " + listeRoles.get(listeImages.indexOf(listeImages.get(victimes.get(i)))) + ". ";
                            }
                            textViews.get(txt).setText(text);
                        } else {
                            txt++;
                            nextButton.callOnClick();
                        }
                    }
                }
                if (ordre.get(roleAct).equals(voyante) && txt == 3) {
                    imageVoyante.setVisibility(View.INVISIBLE);
                    titleText.setText("Loup-Garou");
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

    public void appelVoyante() {
        Intent intent = new Intent(getApplicationContext(), ChoixLG.class);
        ArrayList<String> pListeRoles = new ArrayList<>();
        ArrayList<String> pListeJoueurs = new ArrayList<>();
        ArrayList<Integer> pListeImages = new ArrayList<>();

        for (int i = 0; i < listeImages.size(); i++) {
            if (listeImages.get(i) != R.drawable.voyante) {
                pListeJoueurs.add(listeJoueurs.get(i));
                pListeRoles.add(listeRoles.get(i));
                pListeImages.add(listeImages.get(i));
            }
        }
        intent.putExtra("joueurs", pListeJoueurs);
        intent.putExtra("roles", pListeRoles);
        intent.putExtra("images", pListeImages);
        intent.putExtra("role", "Voyante");
        intent.putExtra("requestCode", 1);
        Resultat.launch(intent);
    }

    public void appelLoup() {
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
        intent.putExtra("requestCode", 1);
        Resultat.launch(intent);
    }

    public void appelSorciere() {
        Intent intent = new Intent(getApplicationContext(), ChoixSorciere.class);
        intent.putExtra("victime", listeJoueurs.get(victimes.get(0)));
        intent.putExtra("potion", potions.get(0));
        intent.putExtra("requestCode", 1);
        Resultat.launch(intent);
        if (potions.get(1) == 1) {
            intent = new Intent(getApplicationContext(), ChoixLG.class);
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
            intent.putExtra("requestCode", 1);
            Resultat.launch(intent);
        }
    }
}