package com.example.applicationv3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class affichage_recette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_recette);
        TextView txt=findViewById(R.id.textView3);
        txt.setText(getIntent().getExtras().getString("nourriture"));
    }
}