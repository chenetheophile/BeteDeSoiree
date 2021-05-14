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
    Item recette;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_recette);
        recette=(Item)getIntent().getExtras().get("Recette");

        TextView txt=findViewById(R.id.listeIngredient);
        txt.setText(recette.getIngredient());

        TextView nomRecette=findViewById(R.id.NomRecette);
        nomRecette.setText(recette.getNom());

        CheckBox fav=findViewById(R.id.fav);
        if(verifier(nomRecette.getText().toString())) {
            fav.setChecked(true);
        }else{
            fav.setChecked(false);
        }
        //verifie que l'utilisateur a deja coché la recette dans une precedente ouverture et coloris l etoile en fonction
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fav.isChecked()){
                    sauver((String) nomRecette.getText(),true,true);
                }else{
                    supprimer((String) nomRecette.getText());
                }//ajoute ou supprime une recette des favoris
            }
        });
        ImageView imgRecette=findViewById(R.id.img_recette);
        Picasso.get().load(recette.getLien()).into(imgRecette);
    }
    private boolean verifier(String recette) {//parcours le fichier des fav et verifie que le nom y est si oui renvoi vrai faux sinon
        boolean verif=false;

        FileInputStream fis;
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
    private void sauver(String recette,boolean ajout,boolean mess){//ajoute une recette a la fin du fichier des fav
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
        private void supprimer(String recette){//supprime une recette des fav
        Toast.makeText(getApplicationContext(),recette+" retiré des favoris",Toast.LENGTH_SHORT).show();
        String liste="\n";
        if(verifier(recette)){
            FileInputStream fis;
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