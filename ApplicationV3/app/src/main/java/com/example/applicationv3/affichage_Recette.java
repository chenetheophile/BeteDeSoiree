package com.example.applicationv3;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class affichage_Recette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_recette);

        TextView txt=findViewById(R.id.listeIngredient);
        txt.setText(getIntent().getExtras().getString("Ingredient"));

        TextView Recette=findViewById(R.id.NomRecette);
        Recette.setText(getIntent().getExtras().getString("Nom"));

        CheckBox fav=findViewById(R.id.fav);
        if(verifier(Recette.getText().toString())) {
            fav.setChecked(true);
        }else{
            fav.setChecked(false);
        }
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fav.isChecked()){
                    sauver((String) Recette.getText(),true,true);
                }else{
                    supprimer((String) Recette.getText());
                }
            }
        });
        ImageView imgRecette=findViewById(R.id.img_recette);
        Picasso.get().load(getIntent().getExtras().getString("lien")).into(imgRecette);
    }
    private boolean verifier(String recette) {
        boolean verif=false;

        FileInputStream fis = null;
        try {
            fis = affichage_Recette.this.openFileInput("Fav");
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                if (line.equalsIgnoreCase(recette)) {
                    verif = true;
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            sauver("",false,false);
        }
        return verif;
    }
    private void sauver(String recette,boolean ajout,boolean mess){
        if(mess){
        Toast.makeText(getApplicationContext(),recette+" Sauvegardé",Toast.LENGTH_SHORT).show();
        }
        try {
            FileOutputStream outputStream;
            recette+='\n';
            String filename="Fav";
            if (ajout) {
                outputStream = openFileOutput(filename, Context.MODE_APPEND);
            } else {
                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            }
            outputStream.write(recette.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        private void supprimer(String recette){
        Toast.makeText(getApplicationContext(),recette+" retiré des favoris",Toast.LENGTH_SHORT).show();
        String liste="\n";
        if(verifier(recette)){
            FileInputStream fis = null;
            try {
                fis = affichage_Recette.this.openFileInput("Fav");
                InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    if(!line.equalsIgnoreCase(recette)){
                        liste=liste+line+'\n';
                    }
                    line = reader.readLine();
                }
                sauver(liste,false,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}