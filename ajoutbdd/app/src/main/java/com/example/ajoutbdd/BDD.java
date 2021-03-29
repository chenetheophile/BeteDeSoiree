package com.example.ajoutbdd;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
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
    public void creerChamp(FirebaseFirestore base,String nomCollection,String nomDocument,ArrayList<String> nomChamp, ArrayList<String> valeur) throws Exception {
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
}
