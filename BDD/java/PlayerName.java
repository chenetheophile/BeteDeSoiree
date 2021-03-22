package com.example.appinnov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class PlayerName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);
        Button SuivantBtn = (Button) findViewById(R.id.SuivantButton);
        EditText NomEditText = (EditText) findViewById(R.id.NomEditText);
        CheckBox SAMCheckBox = (CheckBox) findViewById(R.id.SAMCheckBox);
        final int[] JoueurAct = {1};
        ArrayList<String> Noms = new ArrayList<String>();
        ArrayList<String> SAM = new ArrayList<String>();
        SuivantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().hasExtra("NbJoueurs")) {
                    int NbJoueurs = getIntent().getExtras().getInt("NbJoueurs");
                    if (NomEditText.getText().toString().equalsIgnoreCase("")) {
                        Snackbar.make(v, "Entrez un nom.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else if (Noms.contains(NomEditText.getText().toString())) {
                        Snackbar.make(v, "Ce nom est déjà utilisé.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        Noms.add(NomEditText.getText().toString());
                        if (SAMCheckBox.isChecked()) {
                            SAM.add("SAM");
                        } else {
                            SAM.add("Pas SAM");
                        }
                        if (JoueurAct[0] >= NbJoueurs) {
                            Snackbar.make(v, "Tous les joueurs sont entrés", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            Intent startIntent = new Intent(getApplicationContext(), NameList.class);
                            startIntent.putStringArrayListExtra("Noms", Noms);
                            startIntent.putStringArrayListExtra("SAM", SAM);
                            startIntent.putExtra("NbJoueurs", NbJoueurs);
                            startActivity(startIntent);

                        } else {
                            JoueurAct[0]++;
                            SAMCheckBox.setChecked(false);
                            NomEditText.setText("");
                            NomEditText.setHint("Nom du joueur " + String.valueOf(JoueurAct[0]));
                            if(JoueurAct[0] == NbJoueurs){
                                SuivantBtn.setText("Go !");
                            }
                        }
                    }
                }
            }
        });
    }
}