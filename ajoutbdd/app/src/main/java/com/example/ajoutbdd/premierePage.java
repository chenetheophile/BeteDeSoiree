package com.example.ajoutbdd;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class premierePage extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseFirestore db = new BDD().getBDD();
        setContentView(R.layout.premiere_page);
    }
}