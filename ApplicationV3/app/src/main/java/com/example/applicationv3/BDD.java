package com.example.applicationv3;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BDD {
    private FirebaseFirestore base;
    public BDD(){
    this.base=FirebaseFirestore.getInstance();
    }

    public FirebaseFirestore getBDD(){
        return this.base;//récupere la BDD
    }

    public void creerChamp( String nomCollection, String nomDocument, ArrayList<String> nomChamp, ArrayList<String> valeur) throws Exception {
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
    public void getDocument(Context activity){
        Intent intent=new Intent();
        FirebaseFirestore base=this.getBDD();
        intent.setClass(activity,MenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        ArrayList<String> listeNomCocktail = new ArrayList<>();
        ArrayList<String> listeNomRecette = new ArrayList<>();

        ArrayList<String>lienImgRecette=new ArrayList<>();
        ArrayList<String>lienImgCocktail=new ArrayList<>();

        ArrayList<String>TempsPrepa=new ArrayList<>();

        ArrayList<String>listeIngrCocktail=new ArrayList<>();
        ArrayList<String>listeIngrRecette=new ArrayList<>();

        ArrayList<String>DescR=new ArrayList<>();
        ArrayList<String>DescC=new ArrayList<>();
        base.collection("cocktail")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                listeNomCocktail.add(document.getId());
                                lienImgCocktail.add(document.getString("lien"));
                                listeIngrCocktail.add(document.getString("Ingredient"));
                                DescC.add(document.getString("Description"));

                            }
                            intent.putExtra("listeNomC",listeNomCocktail.toArray(new String[0]));
                            intent.putExtra("lienC",lienImgCocktail.toArray(new String[0]));
                            intent.putExtra("listeIngreC",listeIngrCocktail.toArray(new String[0]));
                            intent.putExtra("Description",DescC.toArray(new String[0]));
                            Log.i("test", "1");
                            base.collection("recette")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {

                                                for (QueryDocumentSnapshot document : task.getResult()) {

                                                    listeNomRecette.add(document.getId());
                                                    lienImgRecette.add(document.getString("lien"));
                                                    listeIngrRecette.add(document.getString("Ingredient"));
                                                    TempsPrepa.add(document.getString("temps"));
                                                    DescR.add(document.getString("Description"));
                                                }
                                                intent.putExtra("listeNomR",listeNomRecette.toArray(new String[0]));
                                                intent.putExtra("lienR",lienImgRecette.toArray(new String[0]));
                                                intent.putExtra("listeIngreR",listeIngrRecette.toArray(new String[0]));
                                                intent.putExtra("Temps",TempsPrepa.toArray(new String[0]));
                                                intent.putExtra("Description",DescR.toArray(new String[0]));
                                                Log.i("test", "2");
                                                activity.startActivity(intent);

                                            }
                                        }

                                    });
                        }
                    }
                });




        }
}