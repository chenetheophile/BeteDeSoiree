package com.example.applicationv3.SansCategorie;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.applicationv3.Recette.affichage_Recette;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BDD {
    private ProgressBar charg;
    private int progress=0;
    private Handler handler=new Handler();
    public BDD(ProgressBar cha){
        this.charg=cha;
    }
    public BDD(){
    }
    public  void addPropo(Context activity,String nom,String ingr,String Desc,String Etape,String Type,int temps){
        String url="http://86.213.175.30/Include/put.php";
        RequestQueue requestQueue = Volley.newRequestQueue(activity);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
        }, error -> error.printStackTrace()){@Override
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
        String url ="http://86.213.175.30/Include/get.php";
        ArrayList<Item> cocktailArrayList=new ArrayList<>();
        ArrayList<Item> recetteArrayList=new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    String[] tab=response.split("%");
                    charg.setMax(tab.length);
                    for (int i=0;i<tab.length;i++){
                        MainActivity.getInstance().updateProgressBar();
                        Gson gson=new Gson();
                        Item item=gson.fromJson(tab[i],Item.class);

                        item.setFavori(new affichage_Recette().verifier(item.getNom(),activity));
                        if (item.getType().equalsIgnoreCase("Recette")){
                            recetteArrayList.add(item);
                        }else if (item.getType().equalsIgnoreCase("Cocktail")){
                            if (item.getLien().equalsIgnoreCase("")){
                                if (item.isAlcool()){
                                    item.setLien("R.drawable.avec_alcool");
                                }else {
                                    item.setLien("R.drawable.sans_alcool");
                                }
                            }
                            cocktailArrayList.add(item);
                        }

                    }
                    Intent intent=new Intent();
                    intent.putExtra("Cocktail",cocktailArrayList);
                    intent.putExtra("Recette",recetteArrayList);
                    intent.setClass(activity, MenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);

                }, error -> Log.i("HTTP","Err:"+error.toString()));

        queue.add(stringRequest);

    }
}