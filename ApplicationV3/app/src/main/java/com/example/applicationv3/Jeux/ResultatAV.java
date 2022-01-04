package com.example.applicationv3.Jeux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.applicationv3.SansCategorie.MainActivity;
import com.example.applicationv3.R;

import java.util.ArrayList;

public class ResultatAV extends AppCompatActivity {

    ArrayList<String> joueurs = new ArrayList<>();
    ArrayList<Integer> rep = new ArrayList<>();
    int tours;

    Button menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_a_v);

        joueurs = getIntent().getStringArrayListExtra("joueurs");
        rep = getIntent().getIntegerArrayListExtra("rep");
        tours = getIntent().getIntExtra("tours", 3);

        ListView resListView = findViewById(R.id.ResListView);
        ResultAdapter resultAdapter = new ResultAdapter(this, joueurs, rep, tours);
        resListView.setAdapter(resultAdapter);

        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}