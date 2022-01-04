package com.example.applicationv3.Jeux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationv3.R;

public class ChoixSorciere extends AppCompatActivity {

    TextView victime;
    Button sauver, rien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_sorciere);

        victime = findViewById(R.id.victimeTextView);
        sauver = findViewById(R.id.sauverButton);
        rien = findViewById(R.id.rienButton);

        victime.setText(getIntent().getStringExtra("victime"));

        sauver.setOnClickListener(v -> {
            if (getIntent().getIntExtra("potion", 0) == 1) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", 1);
                resultIntent.putExtra("role", "Sorcière");
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(ChoixSorciere.this, "La potion de soin a déjà été utilisée...", Toast.LENGTH_SHORT).show();
            }
        });

        rien.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("result", 0);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}