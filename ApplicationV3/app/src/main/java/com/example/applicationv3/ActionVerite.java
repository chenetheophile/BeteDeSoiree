package com.example.applicationv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

public class ActionVerite extends AppCompatActivity {

    private int level = 1;
    private int tours = 3;

    private int idJeu;

    RadioButton Level1RadioButton;
    RadioButton Level2RadioButton;
    RadioButton Level3RadioButton;

    RadioButton Tours3RadioButton;
    RadioButton Tours5RadioButton;
    RadioButton Tours10RadioButton;

    Button JouerButton;
    Button ReglesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_verite);

        idJeu = getIntent().getExtras().getInt("idJeu");

        Level1RadioButton = findViewById(R.id.Level1RadioButton);
        Level2RadioButton = findViewById(R.id.Level2RadioButton);
        Level3RadioButton = findViewById(R.id.LevelESMERadioButton);

        Tours3RadioButton = findViewById(R.id.Tours3RadioButton);
        Tours5RadioButton = findViewById(R.id.Tours5RadioButton);
        Tours10RadioButton = findViewById(R.id.Tours10RadioButton);

        JouerButton = findViewById(R.id.JouerButtonAV);
        ReglesButton = findViewById(R.id.ReglesButton);

        JouerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Level1RadioButton.isChecked()){
                    level = 1;
                } else if (Level2RadioButton.isChecked()){
                    level = 2;
                } else {
                    level = 3;
                }
                if (Tours3RadioButton.isChecked()){
                    tours = 3;
                } else if (Tours5RadioButton.isChecked()){
                    tours = 5;
                } else if (Tours5RadioButton.isChecked()){
                    tours = 10;
                }
                Snackbar.make(v, Joueurs.nomsJoueurs.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ReglesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ReglesJeu.class);
                startIntent.putExtra("idJeu", idJeu);
                startActivity(startIntent);
            }
        });
    }
}