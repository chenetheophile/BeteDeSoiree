package com.example.applicationv3.Jeux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.applicationv3.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Loup_Garou extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loup__garou);

        ArrayList<String> joueurs = getIntent().getStringArrayListExtra("Joueurs");

        Button LocalButton = (Button) findViewById(R.id.LocalButton);
        Button ReseauButton = (Button) findViewById(R.id.ReseauButton);

        LocalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), LGLocal.class);
                startIntent.putExtra("Joueurs", joueurs);
                startActivity(startIntent);
            }
        });

        ReseauButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Fonction reseau en cours de développement.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}