package com.example.applicationv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Proposition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition_recette);

        TextView text=findViewById(R.id.Type);
        text.setText(getIntent().getExtras().getString("type"));
    }
}