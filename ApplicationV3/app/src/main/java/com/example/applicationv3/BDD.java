package com.example.applicationv3;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BDD {
    public BDD(){

    }
    public FirebaseFirestore getBDD(){
        return FirebaseFirestore.getInstance();//récupere la BDD
    }
    public void creerChamp(FirebaseFirestore base, String nomCollection, String nomDocument, ArrayList<String> nomChamp, ArrayList<String> valeur) throws Exception {
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
    public void chercherDB(String collection, String doc, Activity act){
        DocumentReference docRef = this.getBDD().collection(collection).document(doc);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        switch (collection) {
                            case "recette":
                                Intent recipe= new Intent(act, affichage_recette.class);
                                recipe.putExtra("Nom",doc);
                                recipe.putExtra("nourriture", document.getString("Ingredient"));
                                recipe.putExtra("lien", document.getString("lien"));
                                act.startActivity(recipe);
                                break;
                            case "cocktail":
                                Intent cocktail = new Intent(act, affichage_cocktail.class);
                                cocktail.putExtra("boisson", document.getString("Ingredient"));
                                cocktail.putExtra("Nom", doc);
                                cocktail.putExtra("lien", document.getString("lien"));
                                act.startActivity(cocktail);
                                break;
                            default:
                                break;
                        }


                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
    }
}
