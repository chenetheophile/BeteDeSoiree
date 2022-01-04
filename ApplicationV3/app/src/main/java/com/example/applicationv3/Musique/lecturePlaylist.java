package com.example.applicationv3.Musique;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.applicationv3.R;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.protocol.client.CallResult;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

public class lecturePlaylist extends AppCompatActivity {


    private SpotifyAppRemote mSpotifyAppRemote;
    private TextView titrelabel,auteurlabel;
    private ImageButton playimg,previousimg,nextimg;
    private ImageView imgTitre;
    private static final String REDIRECT_URI = "http://com.example.applicationv3/callback";
    private static final String CLIENT_ID = "ec59b9a260ab406fbc577be3bc1d285c";



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
                        connected();

                    }

                    public void onFailure(Throwable throwable) {

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    private void connected() {
        // Play a playlist
        if (!(boolean) getIntent().getExtras().get("lecture")){
            Musique Playlist=(Musique) getIntent().getExtras().get("Playlist");
            String lien="spotify:"+Playlist.getLien();
            mSpotifyAppRemote.getPlayerApi().play(lien);
        }

        // shows info about current track and give possibility to pause skip or restart
        mSpotifyAppRemote.getPlayerApi()
                .subscribeToPlayerState()
                .setEventCallback(playerState -> {
                    final Track track = playerState.track;
                    if (track != null) {
                        titrelabel.setText(track.name);
                        auteurlabel.setText(track.artist.name);
                        mSpotifyAppRemote.getImagesApi().getImage(track.imageUri).setResultCallback(new CallResult.ResultCallback<Bitmap>() {
                            @Override
                            public void onResult(Bitmap bitmap) {
                                imgTitre.setImageBitmap(bitmap);
                            }
                        });
                        playimg.setOnClickListener(v -> mSpotifyAppRemote.getPlayerApi().getPlayerState().setResultCallback(new CallResult.ResultCallback<PlayerState>() {
                            @Override
                            public void onResult(PlayerState playerState1) {
                                if (playerState1.isPaused){
                                    mSpotifyAppRemote.getPlayerApi().resume();
                                    playimg.setImageDrawable(ResourcesCompat.getDrawable(getResources(),R.drawable.ic_pause,getTheme()));
                                }else{
                                    mSpotifyAppRemote.getPlayerApi().pause();
                                    playimg.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_play_arrow,getTheme()));
                                }
                            }
                        }));
                        nextimg.setOnClickListener(v -> mSpotifyAppRemote.getPlayerApi().skipNext());
                    }
                    previousimg.setOnClickListener(v -> mSpotifyAppRemote.getPlayerApi().skipPrevious());
                });
    }


}