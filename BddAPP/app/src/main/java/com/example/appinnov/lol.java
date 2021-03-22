package com.example.appinnov;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class lol extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lol);
        EditText bdd = (EditText) findViewById(R.id.bdd);
        Button Button = (Button) findViewById(R.id.button3);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtbdd = bdd.getText().toString();
                Toast.makeText(lol.this, txtbdd, Toast.LENGTH_LONG).show();
                            }
        });
    }
}