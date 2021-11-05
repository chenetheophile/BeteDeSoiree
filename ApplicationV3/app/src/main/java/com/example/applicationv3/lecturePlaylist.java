package com.example.applicationv3;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

public class lecturePlaylist extends AppCompatActivity {

    private static final String CLIENT_ID="ec59b9a260ab406fbc577be3bc1d285c";
    private static final String REDIRECT_URI = "http://com.example.applicationv3/callback";
    private SpotifyAppRemote mSpotifyAppRemote;
    private TextView titrelabel,auteurlabel;
    private ImageButton playimg,previousimg,nextimg;
    private ImageView imgTitre;
//code recuperer sur spotify developpeur.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_playlist);
        titrelabel=findViewById(R.id.titre);
        auteurlabel=findViewById(R.id.Auteur);
        playimg=findViewById(R.id.play);
        previousimg=findViewById(R.id.previous);
        nextimg=findViewById(R.id.next);
        imgTitre=findViewById(R.id.imgMusique);

    }
    @Override
    protected void onStart() {
        super.onStart();
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("Test", "Connected!");
                        connected();

                    }

                    public void onFailure(Throwable throwable) {
                        Log.e("Test", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Test","Deconection");
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    private void connected() {
        // Play a playlist
        Log.i("Test","connecté");
        Item Playlist=(Item)getIntent().getExtras().get("Playlist");
        String lien="spotify:"+Playlist.getIngredient();
        Log.i("Test", String.valueOf(mSpotifyAppRemote.getPlayerApi().play(lien)));

        // Subscribe to PlayerState
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        Log.d("Test", track.name + " by " + track.artist.name);
                        titrelabel.setText(track.name);
                        auteurlabel.setText(track.artist.name);
                        mSpotifyAppRemote.getImagesApi().getImage(track.imageUri).setResultCallback(new CallResult.ResultCallback<Bitmap>() {
                            @Override
                            public void onResult(Bitmap bitmap) {
                                imgTitre.setImageBitmap(bitmap);
                            }
                        });
                        playimg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSpotifyAppRemote.getPlayerApi().getPlayerState().setResultCallback(new CallResult.ResultCallback<PlayerState>() {
                                    @Override
                                    public void onResult(PlayerState playerState) {
                                        if (playerState.isPaused){
                                            mSpotifyAppRemote.getPlayerApi().resume();
                                            playimg.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_pause,getTheme()));
                                        }else{
                                            mSpotifyAppRemote.getPlayerApi().pause();
                                            playimg.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_play_arrow,getTheme()));
                                        }
                                    }
                                });
                            }
                        });
                        nextimg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSpotifyAppRemote.getPlayerApi().skipNext();
                            }
                        });
                    }
                    previousimg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mSpotifyAppRemote.getPlayerApi().skipPrevious();
                        }
                    });
                });
    }
}