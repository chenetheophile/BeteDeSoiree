package com.example.applicationv3;

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
    private static ArrayList<Item> musique=new ArrayList<>();
    private static ArrayList<String> Playlist=new ArrayList<>();
    private static ArrayList<Integer> images=new ArrayList<>();
    private static ArrayList<String> Detail=new ArrayList<>();
    private static ArrayList<String> Desc=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_perso);
        Button btnAdd=findViewById(R.id.boutonRecherchePlaylist);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SearchSongsActivity.class);
                startActivityForResult(intent,1);
            }
        });
        RecyclerView playlist=findViewById(R.id.recyclerPlaylist);

        for(int i=0;i<Playlist.size();i++){
            musique.add(new Item("Musique",Playlist.get(i),Detail.get(i),Desc.get(i),true));
        }
        JeuxAdapter adapterPlaylist = new JeuxAdapter(getApplicationContext(), musique, images);
        playlist.setAdapter(adapterPlaylist);
        playlist.setAdapter(adapterPlaylist);
        playlist.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            Bundle donneeMusique=data.getExtras();
            Playlist.add((String) donneeMusique.get("Name"));
            Detail.add((String) donneeMusique.get("Author"));
            images.add((Integer) donneeMusique.get("Img"));
            recreate();
        }
    }
}