package com.example.applicationv3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class affichage_Recette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_recette);

        try {
            verifier();
        } catch (FileNotFoundException e) {
        }
        TextView txt=findViewById(R.id.listeIngredient);
        txt.setText(getIntent().getExtras().getString("Ingredient"));

        TextView Recette=findViewById(R.id.NomRecette);
        Recette.setText(getIntent().getExtras().getString("Nom"));

        CheckBox fav=findViewById(R.id.fav);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fav.isChecked()){
                    sauver((String) Recette.getText());
                }else{
                    supprimer((String) Recette.getText());
                }
            }
        });
        ImageView imgRecette=findViewById(R.id.img_recette);
        Picasso.get().load(getIntent().getExtras().getString("lien")).into(imgRecette);
    }
    private void verifier() throws FileNotFoundException {
        FileInputStream fis = null;
        fis = affichage_Recette.this.openFileInput("Fav");
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
        } finally {
            String contents = stringBuilder.toString();
            Log.i("Fav",contents);
        }
    }
    private void sauver(String recette){
        Toast.makeText(getApplicationContext(),recette+" Sauvegardé",Toast.LENGTH_SHORT).show();
        String filename = "Fav";
        try {
            try (FileOutputStream fos = getApplicationContext().openFileOutput(filename, Context.MODE_PRIVATE)) {
                fos.write(recette.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void supprimer(String recette){
        Toast.makeText(getApplicationContext(),recette+" retiré des favoris",Toast.LENGTH_SHORT).show();
    }
}