package com.example.applicationv3.Recette;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationv3.R;
import com.example.applicationv3.SansCategorie.EtapeAdapter;
import com.example.applicationv3.SansCategorie.Item;
import com.example.applicationv3.SansCategorie.donnee;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class affichage_Recette extends AppCompatActivity{
    Item recette;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_recette);
        recette=(Item)getIntent().getExtras().get("Recette");

        TextView txt=findViewById(R.id.listeIngredient);
        txt.setText(recette.getIngredient().replace("@","\n"));

        TextView Description=findViewById(R.id.description);
        Description.setText(recette.getDescription());

        TextView nomRecette=findViewById(R.id.NomRecette);
        nomRecette.setText(recette.getNom());

        TextView time=findViewById(R.id.time);
        ImageView ic_time=findViewById(R.id.ic_time);


        RecyclerView etap=findViewById(R.id.EtapeRecycler);
        ArrayList<String>listeEtape=new ArrayList<>();
        for(int i=0;i<recette.getEtape().split("@").length;i++){
            listeEtape.add(String.valueOf(recette.getEtape().split("@")[i]));
        }

        EtapeAdapter etapeAdapter=new EtapeAdapter(getApplicationContext(),listeEtape);
        etap.setAdapter(etapeAdapter);
        etap.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        if (recette.getType().equalsIgnoreCase("Recette")){
            time.setText(recette.getTempsPrepa());
            time.setVisibility(View.VISIBLE);
            ic_time.setVisibility(View.VISIBLE);
        }else{
            time.setVisibility(View.GONE);
            ic_time.setVisibility(View.GONE);
        }
        CheckBox fav=findViewById(R.id.fav);
        if(recette.isFavori()) {
            fav.setChecked(true);
        }else{
            fav.setChecked(false);
        }
        //verifie que l'utilisateur a deja coché la recette dans une precedente ouverture et coloris l etoile en fonction
        fav.setOnClickListener(v -> {
            if(fav.isChecked()){
                recette.setFavori(true);
                sauver((String) nomRecette.getText(),true,true,getApplicationContext());


            }else{
                recette.setFavori(false);
                supprimer((String) nomRecette.getText(),getApplicationContext());

            }//ajoute ou supprime une recette des favoris
            ((donnee)getApplication()).getAdapterCocktail().notifyDataSetChanged();
            ((donnee)getApplication()).getAdapterRecette().notifyDataSetChanged();
        });
        ImageView imgRecette=findViewById(R.id.img_recette);
        if (!recette.getLien().equalsIgnoreCase("")){
            Picasso.get().load(recette.getLien()).into(imgRecette);
        }

    }

    public boolean verifier(String recette, Context act) {//parcours le fichier des fav et verifie que le nom y est si oui renvoi vrai faux sinon
        boolean verif=false;

        FileInputStream fis;
        try {
            fis =act.openFileInput("Fav");
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
            sauver("",false,false,act);
        }
        return verif;
    }
    private void sauver(String recette,boolean ajout,boolean mess,Context act){//ajoute une recette a la fin du fichier des fav
        if(mess){
        Toast.makeText(getApplicationContext(),recette+" Sauvegardé",Toast.LENGTH_SHORT).show();
        }
        try {
            FileOutputStream outputStream;
            recette+='\n';
            String filename="Fav";
            if (ajout) {
                outputStream = act.openFileOutput(filename, Context.MODE_APPEND);

            } else {
                outputStream = act.openFileOutput(filename, Context.MODE_PRIVATE);
            }
            outputStream.write(recette.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
        private void supprimer(String recette,Context act){//supprime une recette des fav
        Toast.makeText(getApplicationContext(),recette+" retiré des favoris",Toast.LENGTH_SHORT).show();
        String liste="\n";
        if(verifier(recette,getApplicationContext())){
            FileInputStream fis;
            try {
                fis = act.openFileInput("Fav");
                InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    if(!line.equalsIgnoreCase(recette)){
                        liste=liste+line+'\n';
                    }
                    line = reader.readLine();
                }
                sauver(liste,false,false,act);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}