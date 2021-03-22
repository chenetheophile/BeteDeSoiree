package com.example.appinnov;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class NameList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> Noms = new ArrayList<String>();
    ArrayList<String> SAM = new ArrayList<String>();
    int imageList[] = {R.drawable.happy, R.drawable.angry, R.drawable.shocked, R.drawable.cool, R.drawable.bored};
    ArrayList<Integer> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_name_list);
        super.onCreate(savedInstanceState);

        Button ValiderButton = (Button) findViewById(R.id.ValiderButton);
        Button RetourButton = (Button) findViewById(R.id.RetourButton);
        Noms = getIntent().getExtras().getStringArrayList("Noms");
        SAM = getIntent().getExtras().getStringArrayList("SAM");
        int NbJoueurs = getIntent().getExtras().getInt("NbJoueurs");

        for (int i = 0; i < NbJoueurs; i++) {
            images.add(imageList[new Random().nextInt(5)]);
        }

        RetourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });

        ValiderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(startIntent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        MyAdapter myAdapter = new MyAdapter(this, Noms, SAM, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}