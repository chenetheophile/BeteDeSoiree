package com.example.applicationv3.SansCategorie;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.applicationv3.R;

public class MainActivity extends AppCompatActivity {
    private BDD bdd;
    private ProgressBar charg;
    private static MainActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        activity=this;
        charg=findViewById(R.id.chargement);
        bdd=new BDD(charg);
        Button StartButton =findViewById(R.id.StartButton);
        StartButton.setOnClickListener(v -> {
            charg.setVisibility(View.VISIBLE);
            bdd.getDocument(activity);
        });
    }
    public static MainActivity getInstance() {
        return activity;
    }
    public void updateProgressBar(){
            charg.setProgress(charg.getProgress()+1,true);
    }

}