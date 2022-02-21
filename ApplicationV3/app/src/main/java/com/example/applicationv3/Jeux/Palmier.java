package com.example.applicationv3.Jeux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.applicationv3.R;

public class Palmier extends AppCompatActivity {

    private Button jouerButton, reglesButton;
    private int idJeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palmier);

        idJeu = getIntent().getIntExtra("IdJeu", -1);
        jouerButton = findViewById(R.id.JouerButtonP);
        reglesButton = findViewById(R.id.ReglesButtonP);
        jouerButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, PalmierJeu.class);
            intent.putExtra("IdJeu", idJeu);
            startActivity(intent);
        });
        reglesButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ReglesJeu.class);
            intent.putExtra("IdJeu", idJeu);
            startActivity(intent);
        });
    }
}