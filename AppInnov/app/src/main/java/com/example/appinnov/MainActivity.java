package com.example.appinnov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button GoButton = (Button) findViewById(R.id.GoButton);
        EditText NbJoueursEditText = (EditText) findViewById(R.id.NbJoueursEditText);
        CheckBox CartesCheckBox = (CheckBox) findViewById(R.id.CartesCheckBox);
        CheckBox DesCheckBox = (CheckBox) findViewById(R.id.DesCheckBox);
        boolean cartes = CartesCheckBox.isChecked();
        boolean des = DesCheckBox.isChecked();
        GoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int NbJoueurs;
                if (NbJoueursEditText.getText().toString().equalsIgnoreCase("")) {
                    NbJoueurs = 0;
                } else {
                    NbJoueurs = Integer.parseInt(NbJoueursEditText.getText().toString());
                }
                if (NbJoueurs > 1 && NbJoueurs < 21) {
                    Intent startIntent = new Intent(getApplicationContext(), PlayerName.class);
                    startIntent.putExtra("NbJoueurs", NbJoueurs);
                    startIntent.putExtra("cartes", cartes);
                    startIntent.putExtra("des", des);
                    startActivity(startIntent);
                } else if (NbJoueurs < 2){
                    Snackbar.make(v, "Entrez un nombre de joueurs supérieur à 1", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else if (NbJoueurs > 10){
                    Snackbar.make(v, "Entrez un nombre de joueurs inférieur à 20", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

    }
}