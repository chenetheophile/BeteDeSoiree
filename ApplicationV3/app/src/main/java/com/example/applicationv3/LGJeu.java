package com.example.applicationv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class LGJeu extends AppCompatActivity {

    ArrayList<String> ordre = new ArrayList<>(Arrays.asList("Voyante", "Loup-Garou", "Sorcière"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_g_jeu);
    }
}