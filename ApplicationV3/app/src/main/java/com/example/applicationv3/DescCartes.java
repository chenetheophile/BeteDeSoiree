package com.example.applicationv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applicationv3.R;

import java.util.ArrayList;
import java.util.Arrays;

public class DescCartes extends AppCompatActivity {

    private int pos;
    private String[] noms;
    private String[] desc;
    public ArrayList<Integer> imagesCartes = new ArrayList<>(Arrays.asList(R.drawable.loupgarou, R.drawable.voyante, R.drawable.petitefille, R.drawable.cupidon, R.drawable.chasseur, R.drawable.sorciere, R.drawable.voleur, R.drawable.villageois));

    private TextView nomTextView, descTextView;
    private ImageView carteImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_cartes);

        pos = getIntent().getExtras().getInt("pos");
        noms = getResources().getStringArray(R.array.cartes_lg);
        desc = getResources().getStringArray(R.array.desc_cartes);

        nomTextView = findViewById(R.id.nomCarteDescTextView);
        descTextView = findViewById(R.id.descCarteTextView);
        carteImageView = findViewById(R.id.carteDescImageView);

        nomTextView.setText(noms[pos]);
        descTextView.setText(desc[pos].replace("@", "'"));
        carteImageView.setImageResource(imagesCartes.get(pos));
    }
}