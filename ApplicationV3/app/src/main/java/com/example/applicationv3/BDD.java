package com.example.applicationv3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BDD {
    private FirebaseFirestore base;
    private FirebaseUser User;
    private ProgressBar charg;

    public BDD(FirebaseUser usr){
        this.User=usr;
        this.base=FirebaseFirestore.getInstance();
    }
    public BDD(FirebaseUser usr,ProgressBar chargement){
        this.User=usr;
        this.charg=chargement;
        this.base=FirebaseFirestore.getInstance();
    }
    public  BDD(){
        this.User=null;
        this.base=FirebaseFirestore.getInstance();
    }

    public FirebaseFirestore getBDD(){
        return this.base;//récupere la BDD
    }

    public void creerChamp( String nomCollection, String nomDocument, ArrayList<String> nomChamp, ArrayList<String> valeur) throws Exception {//sert a creer un champ dans un document de la DB servira pour les admins
        FirebaseFirestore base=this.getBDD();
        if(nomChamp.size()!=valeur.size()){
            throw new Exception("Difference du nombre d'element entre nomChamp et Valeur");
        }
        else{
            Map<String, Object> map = new HashMap<>();
            for(int i=0;i<nomChamp.size();i++){
                map.put(nomChamp.get(i),valeur.get(i));
            }
            base.collection(nomCollection).document(nomDocument)
                    .set(map)
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("BDD", "Error adding document", e);
                        }
                    });
        }
    }

    public void getDocument(Context activity){//recupere le contenue de la DB et le transmet dans la suite de l'app
        Intent intent=new Intent();
        intent.putExtra("User",User);
        FirebaseFirestore base=this.getBDD();
        intent.setClass(activity,MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ArrayList<Item> cocktailArrayList=new ArrayList<>();
        ArrayList<Item> recetteArrayList=new ArrayList<>();
        base.collection("cocktail")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int nbCocktail=task.getResult().size();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                cocktailArrayList.add(new Item(new affichage_Recette().verifier(document.getId(),activity),document.getId(),document.getString("lien"),document.getString("Ingredient"),document.getString("Description")));
                              updateProgressBar((int)50/nbCocktail);
                            }
                            intent.putExtra("Cocktail",cocktailArrayList);
                            base.collection("recette")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                int nbRecette=task.getResult().size();
                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                    recetteArrayList.add(new Item(new affichage_Recette().verifier(document.getId(),activity),document.getId(),document.getString("lien"),document.getString("Ingredient"),document.getString("Description"),document.getString("temps")));
                                                    updateProgressBar((int)50/nbRecette);
                                                }
                                                intent.putExtra("Recette",recetteArrayList);
                                                charg.setVisibility(View.INVISIBLE);
                                                charg.setProgress(0);
                                                activity.startActivity(intent);

                                            }
                                        }

                                    });
                        }
                    }
                });




        }
    public void updateProgressBar(int progress){
        charg.setProgress(charg.getProgress()+progress);
    }
}