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
import com.android.volley.toolbox.JsonObjectRequest;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BDD {
    private ProgressBar charg;
    public BDD(ProgressBar cha){
        this.charg=cha;
    }
    public BDD(){
    }
    public  void addPropo(Context activity,String nom,String ingr,String Desc,String Etape,String Type,int temps){
        String url="http://109.215.52.234/Include/put.php";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("HTTP",response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){@Override
        protected Map<String,String> getParams(){
            Map<String,String> params = new HashMap<>();

            params.put("nom", nom);
            params.put("ingr", ingr);
            params.put("desc", Desc);
            params.put("etape", Etape);
            params.put("type", Type);
            params.put("lien", "NA");
            params.put("alcool", "0");
            params.put("table", "proposition");
            params.put("temps",temps+"min" );
            params.put("princ", "NA");
            return params;
        }
        };

        requestQueue.add(stringRequest);
    }
    public void getDocument(Context activity){//recupere le contenue de la DB et le transmet dans la suite de l'app

        RequestQueue queue = Volley.newRequestQueue(activity);
        String url ="http://109.215.52.234/Include/get.php";
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