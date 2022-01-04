package com.example.applicationv3.Musique;

import android.content.Context;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.applicationv3.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchSongsActivity extends AppCompatActivity {
    private MusiqueAdapter adapter;
    private Context context;
    private String token = "";

    private ArrayList<Musique> listeMusiqueTrouver = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_songs);

        context = this;
        token= (String) getIntent().getExtras().get("token");

        RecyclerView affichageresultat = findViewById(R.id.recyclerRechercheTitre);

        EditText titre = findViewById(R.id.InputRecherche);
        titre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count <= 5) {
                    String url = "http://109.215.52.234/Include/search.php";
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
                        try {
                            listeMusiqueTrouver = getMusique(response);
                        } catch (Exception e) {
                            Log.e("Error getMusique()", e.toString());
                        }

                        adapter = new MusiqueAdapter(context, listeMusiqueTrouver);
                        affichageresultat.setAdapter(adapter);
                    }, error -> error.printStackTrace()) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("recherche", s.toString());
                            params.put("token", token);
                            params.put("longueur", "50");
                            return params;
                        }
                    };

                    requestQueue.add(stringRequest);
                }
                if (adapter != null) {
                    Filter filter = adapter.getFilter();
                    filter.filter(s);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        affichageresultat.setLayoutManager(new LinearLayoutManager(getApplication()));
    }
    public ArrayList<Musique> getMusique(String JsonString) {
        ArrayList<Musique> val = new ArrayList<>();
        JsonObject tracks = (JsonObject) ((JsonObject) JsonParser.parseString(String.valueOf(JsonString))).get("tracks");
        JsonArray items = (JsonArray) tracks.get("items");
        for (int i = 0; i < items.size(); i++) {
            String uri = String.valueOf(((JsonObject) items.get(i)).get("uri"));
            String nom = String.valueOf(((JsonObject) items.get(i)).get("name"));
            String duree = mstoMin(String.valueOf(((JsonObject) items.get(i)).get("duration_ms")));

            JsonObject album = (JsonObject) ((JsonObject) items.get(i)).get("album");
            JsonArray img = (JsonArray) album.get("images");
            String image = String.valueOf(((JsonObject) img.get(2)).get("url"));

            JsonArray artists = (JsonArray) ((JsonObject) items.get(i)).get("artists");
            String listeArtiste = "";
            for (int j = 0; j < artists.size(); j++) {
                listeArtiste += ((JsonObject) artists.get(j)).get("name") + ", ";
            }
            val.add(new Musique(nom.replace("\"", ""), listeArtiste.replace("\"", ""), uri.replace("\"", ""), duree.replace("\"", ""), image.replace("\"", "")));
        }
        return val;
    }

    private String mstoMin(String duration_ms) {
        int duree = Integer.parseInt(duration_ms);
        int min = (int) duree / 60000;
        int sec = (int) (duree - min * 60000) / 1000;
        return min + "min" + sec;
    }
}