package com.example.applicationv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    private BDD bdd=new BDD(null);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View par=findViewById(R.id.parametrefrag);
        par.setVisibility(View.GONE);
        Button StartButton = (Button) findViewById(R.id.StartButton);
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bdd.getDocument(getApplicationContext());
            }
        });
        ImageButton param=findViewById(R.id.parametre);
        param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                par.setVisibility(View.VISIBLE);
            }
        });
    }

}