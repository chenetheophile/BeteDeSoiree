package com.example.applicationv3;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Filter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchSongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerView affichageresultat=findViewById(R.id.recyclerRechercheTitre);

        RechercheMusiqueAdapter adapter=new RechercheMusiqueAdapter(getApplicationContext());
        affichageresultat.setAdapter(adapter);
        affichageresultat.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        EditText titre=findViewById(R.id.InputRecherche);
        titre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Filter filter= adapter.getFilter();
                filter.filter(titre.getText().toString().toLowerCase().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        setContentView(R.layout.activity_search_songs);
    }
}