package com.example.applicationv3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class affichage_recette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BDD db=new BDD();
        setContentView(R.layout.activity_affichage_recette);
    }
}