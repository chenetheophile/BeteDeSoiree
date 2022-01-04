package com.example.applicationv3.Jeux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.applicationv3.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class AVJeu extends AppCompatActivity {

    Button actionButton, veriteButton;
    TextView nomJoueurTextView, choixTextView, actVerTextView;

    int joueur = 0;
    int tourAct = 0;
    int tours;
    int level;

    ArrayList<String> joueurs = new ArrayList<>();
    ArrayList<Integer> rep = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_v_jeu);

        tours = getIntent().getIntExtra("tours", 3);
        level = getIntent().getIntExtra("level", 2);

        actionButton = findViewById(R.id.ActionButton);
        veriteButton = findViewById(R.id.VeriteButton);
        nomJoueurTextView = findViewById(R.id.nomJoueurTextView);
        choixTextView = findViewById(R.id.choixTextView);
        actVerTextView = findViewById(R.id.actVerTextView);

        joueurs = getIntent().getStringArrayListExtra("joueurs");

        nomJoueurTextView.setText(joueurs.get(joueur));

        actionButton.setOnClickListener(this::actionClick);
        veriteButton.setOnClickListener(this::veriteClick);
    }

    private void veriteClick(View view) {
        choixTextView.setText("Vérité !");
        actVerTextView.setText("Vérité de niveau " + String.valueOf(level));
        actionButton.setText("J'ai répondu !");
        veriteButton.setText("Je refuse...");
        actionButton.setOnClickListener(this::doneClick);
        veriteButton.setOnClickListener(this::failedClick);
    }

    public void actionClick(View v) {
        choixTextView.setText("Action !");
        actVerTextView.setText("Action de niveau " + String.valueOf(level));
        actionButton.setText("C'est fait !");
        veriteButton.setText("Je refuse...");
        actionButton.setOnClickListener(this::doneClick);
        veriteButton.setOnClickListener(this::failedClick);
    }

    public void doneClick(View v) {
        if (tourAct == 0){
            rep.add(1);
        } else {
            rep.set(joueur, rep.get(joueur) + 1);
        }

        if (joueur < joueurs.size() - 1) {
            joueur++;
        } else if (tourAct < tours - 1) {
            joueur = 0;
            tourAct++;
        } else {
            Intent intent = new Intent(getApplicationContext(), ResultatAV.class);
            intent.putExtra("joueurs", joueurs);
            intent.putExtra("rep", rep);
            intent.putExtra("tours", tours);
            startActivity(intent);
            return;
        }
        nomJoueurTextView.setText(joueurs.get(joueur));
        choixTextView.setText("Action ou Vérité ?");
        actVerTextView.setText("");
        actionButton.setText("Action");
        veriteButton.setText("Vérité");
        actionButton.setOnClickListener(this::actionClick);
        veriteButton.setOnClickListener(this::veriteClick);
    }

    public void failedClick(View v) {
        Snackbar.make(v, "Les autres joueurs doivent te trouver un gage !", Snackbar.LENGTH_LONG).show();
        if (tourAct == 0){
            rep.add(0);
        }
        if (joueur < joueurs.size() - 1) {
            joueur++;
        } else if (tourAct < tours - 1) {
            joueur = 0;
            tourAct++;
        } else {
            Intent intent = new Intent(getApplicationContext(), ResultatAV.class);
            intent.putExtra("joueurs", joueurs);
            intent.putExtra("rep", rep);
            intent.putExtra("tours", tours);
            startActivity(intent);
            return;
        }
        nomJoueurTextView.setText(joueurs.get(joueur));
        choixTextView.setText("Action ou Vérité ?");
        actVerTextView.setText("");
        actionButton.setText("Action");
        veriteButton.setText("Vérité");
        actionButton.setOnClickListener(this::actionClick);
        veriteButton.setOnClickListener(this::veriteClick);
    }
}