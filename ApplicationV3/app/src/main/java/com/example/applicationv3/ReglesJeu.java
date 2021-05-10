package com.example.applicationv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ReglesJeu extends AppCompatActivity {

    TextView TitleTextView;
    TextView ReglesTextView;
    int idJeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regles_jeu);

        TitleTextView = findViewById(R.id.TitleTextViewRegles);
        ReglesTextView = findViewById(R.id.TextViewReglesJeu);

        idJeu = getIntent().getExtras().getInt("IdJeu");

        TitleTextView.setText(getResources().getStringArray(R.array.noms_jeux)[idJeu]);
        ReglesTextView.setText(getResources().getStringArray(R.array.desc_jeux)[idJeu]);
    }
}