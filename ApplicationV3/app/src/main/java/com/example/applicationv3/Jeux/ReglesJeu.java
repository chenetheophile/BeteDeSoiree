package com.example.applicationv3.Jeux;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.applicationv3.R;

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

        String[] noms = getResources().getStringArray(R.array.noms_jeux);
        String[] desc = getResources().getStringArray(R.array.desc_jeux);

        TitleTextView.setText(noms[idJeu]);
        ReglesTextView.setText(desc[idJeu]);
    }
}