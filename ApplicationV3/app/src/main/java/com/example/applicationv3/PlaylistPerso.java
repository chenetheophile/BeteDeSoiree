package com.example.applicationv3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlaylistPerso extends AppCompatActivity {
    private static ArrayList<Musique> musique=new ArrayList<>();
    private static ArrayList<String> Playlist=new ArrayList<>();
    private static ArrayList<Integer> images=new ArrayList<>();
    private static ArrayList<String> Detail=new ArrayList<>();
    private static ArrayList<String> Desc=new ArrayList<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_perso);

        if (getApplicationContext().getClass().equals(donnee.class)){
            donnee donnee=(donnee) getApplicationContext();
            context=donnee.getAdapterCocktail().getContext();
        }else{
            context=getApplicationContext();
        }

        Button btnAdd=findViewById(R.id.boutonRecherchePlaylist);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SearchSongsActivity.class);
                startActivityForResult(intent,1);
            }
        });
        RecyclerView playlist=findViewById(R.id.recyclerPlaylist);
        RechercheMusiqueAdapter adapterPlaylist = new RechercheMusiqueAdapter(context, musique);
        playlist.setAdapter(adapterPlaylist);
        playlist.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}