package com.example.applicationv3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import java.util.ArrayList;

public class PlaylistPerso extends AppCompatActivity {
    private static ArrayList<Musique> musique=new ArrayList<>();
    private static ArrayList<Musique> queue=new ArrayList<>();
    private Context context;

    private RechercheMusiqueAdapter adapterPlaylist;
    private static final String CLIENT_ID="ec59b9a260ab406fbc577be3bc1d285c";
    private static final String REDIRECT_URI = "http://com.example.applicationv3/callback";
    private SpotifyAppRemote mSpotifyAppRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_perso);
        context=this;
        musique.clear();
        Button btnAdd=findViewById(R.id.boutonRecherchePlaylist);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SearchSongsActivity.class);
                startActivityForResult(intent,1);
            }
        });


        RecyclerView playlist=findViewById(R.id.recyclerPlaylist);
        adapterPlaylist = new RechercheMusiqueAdapter(context, musique);
        playlist.setAdapter(adapterPlaylist);
        playlist.setLayoutManager(new LinearLayoutManager(this));
        adapterPlaylist.updateData(new Musique("Aucune musique","","","",""),true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("ActivityResult",((Musique) data.getExtras().get("Musique")).toString());
        Musique newMusic=((Musique) data.getExtras().get("Musique"));
        if (resultCode==RESULT_OK){
            adapterPlaylist.updateData(newMusic,true);
            musique.add(newMusic);
        }
        lire(newMusic);

    }

    private void lire(Musique newMusic) {
        queue.add(newMusic);
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(false)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.i("Test","connecté");
                        if (queue.size()==1){
                            mSpotifyAppRemote.getPlayerApi().play(newMusic.getLien());
                        }else{
                            mSpotifyAppRemote.getPlayerApi().queue(newMusic.getLien());
                        }

                    }
                    public void onFailure(Throwable throwable) {
                        Log.e("Test", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }
}