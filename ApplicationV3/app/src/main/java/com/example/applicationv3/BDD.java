package com.example.applicationv3;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BDD {
    private FirebaseFirestore base;
    private FirebaseUser User;
    private ProgressBar charg;


    public BDD(FirebaseUser usr, ProgressBar chargement){
        this.User=usr;
        this.charg=chargement;
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

        RequestQueue queue = Volley.newRequestQueue(activity);
        String url ="http://192.168.1.14/Include/get.php";
        ArrayList<Item> cocktailArrayList=new ArrayList<>();
        ArrayList<Item> recetteArrayList=new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("HTTP",response);
                        String[] tab=response.split("%");
                        for (int i=0;i<tab.length;i++){
                            Gson gson=new Gson();
                            Item item=gson.fromJson(tab[i],Item.class);
                            item.setFavori(new affichage_Recette().verifier(item.getNom(),activity));
                            if (item.getType().equalsIgnoreCase("Recette")){
                                recetteArrayList.add(item);
                            }else if (item.getType().equalsIgnoreCase("Cocktail")){
                                cocktailArrayList.add(item);
                            }
                            updateProgressBar((int)50/tab.length);
                        }
                        Intent intent=new Intent();
                        intent.putExtra("Cocktail",cocktailArrayList);
                        intent.putExtra("Recette",recetteArrayList);
                        charg.setVisibility(View.INVISIBLE);
                        charg.setProgress(0);
                        intent.setClass(activity,MenuActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        activity.startActivity(intent);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("HTTP","Err:"+error.toString());
            }
        });

        queue.add(stringRequest);

        }
    public void updateProgressBar(int progress){
        charg.setProgress(charg.getProgress()+progress);
    }
}