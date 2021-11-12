package com.example.applicationv3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Filter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SearchSongsActivity extends AppCompatActivity {
    private static final String REDIRECT_URI = "http://com.example.applicationv3/callback";
    private static final String CLIENT_ID="ec59b9a260ab406fbc577be3bc1d285c";
    private static final int REQUEST_CODE = 1337;
    private RechercheMusiqueAdapter adapter;
    private Context context;
    private String token="";
    private final ScheduledExecutorService tokenrefresh= Executors.newScheduledThreadPool(1);
    private ArrayList<Musique>listeMusiqueTrouver=new ArrayList<>();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_songs);

        context=this;
        RecyclerView affichageresultat=findViewById(R.id.recyclerRechercheTitre);
        tokenrefresh.scheduleAtFixedRate(refreshToken(),0,1, TimeUnit.HOURS);

        EditText titre=findViewById(R.id.InputRecherche);
        titre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count<=5){
                    String url="http://109.215.52.234/Include/search.php";
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                listeMusiqueTrouver=getMusique(response);
                            }catch (Exception e){
                                Log.e("Error getMusique()",e.toString());
                            }

                            for (Musique musique:listeMusiqueTrouver){
                                Log.i("Json",musique.toString());
                            }
                            Log.i("Adapter", listeMusiqueTrouver.toString());
                            adapter=new RechercheMusiqueAdapter(context,listeMusiqueTrouver);
                            affichageresultat.setAdapter(adapter);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }){@Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<>();
                        params.put("recherche",s.toString());
                        params.put("token",token);
                        params.put("longueur","50");
                        return params;
                    }
                    };

                    requestQueue.add(stringRequest);
                }
                if (adapter!=null){
                    Filter filter= adapter.getFilter();
                    filter.filter(s);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        affichageresultat.setLayoutManager(new LinearLayoutManager(getApplication()));
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        // Check if result comes from the correct activity
        AuthorizationResponse response=null;
        if (requestCode == REQUEST_CODE) {
            response= AuthorizationClient.getResponse(resultCode, intent);

            Log.i("spot",response.getType().toString());
            switch (response.getType()) {
                // Response was successful and contains auth token
                case TOKEN:
                    setToken(response.getAccessToken());
                    break;

                // Auth flow returned an error
                case ERROR:
                    Log.e("erreur Spotify", response.getError());
                    // Handle error response
                    break;

                // Most likely auth flow was cancelled
                default:
                    // Handle other cases
            }
        }
    }
    public ArrayList<Musique> getMusique(String JsonString){
        ArrayList<Musique> val=new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonObject tracks= (JsonObject) ((JsonObject) parser.parse(String.valueOf(JsonString))).get("tracks");
        JsonArray items= (JsonArray) tracks.get("items");
        for (int i=0;i<items.size();i++){
            String uri=String.valueOf(((JsonObject)items.get(i)).get("uri"));
            String nom=String.valueOf(((JsonObject)items.get(i)).get("name"));
            String duree=mstoMin(String.valueOf(((JsonObject)items.get(i)).get("duration_ms")));

            JsonObject album= (JsonObject) ((JsonObject)items.get(i)).get("album");
            JsonArray img=(JsonArray)album.get("images");
            String image=String.valueOf(((JsonObject)img.get(2)).get("url"));

            JsonArray artists=(JsonArray) ((JsonObject)items.get(i)).get("artists");
            String listeArtiste="";
            for (int j=0;j<artists.size();j++){
                listeArtiste+=((JsonObject)artists.get(j)).get("name")+", ";
            }
            val.add(new Musique(nom.replace("\"",""),listeArtiste.replace("\"",""),uri.replace("\"",""),duree.replace("\"",""),image.replace("\"","")));
        }
        return val;
    }

    private String mstoMin(String duration_ms) {
        int duree= Integer.parseInt(duration_ms);
        int min= (int)duree/60000;
        int sec=(int)(duree-min*60000)/1000;
        return min+"min"+sec;
    }
    private Runnable refreshToken() {
        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                AuthorizationRequest.Builder builder =
                        new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);

                builder.setScopes(new String[]{"playlist-read-private"});
                AuthorizationRequest request = builder.build();

                Intent intent = AuthorizationClient.createLoginActivityIntent((Activity) context, request);
                startActivityForResult(intent, REQUEST_CODE);
            }
        };
        return runnable;
    }
}