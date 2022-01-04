package com.example.applicationv3.Musique;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationv3.R;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlaylistPerso extends AppCompatActivity {
    private static ArrayList<Musique> musique=new ArrayList<>();
    private static ArrayList<Musique> queue=new ArrayList<>();
    private MusiqueAdapter adapterPlaylist;
    private SpotifyAppRemote mSpotifyAppRemote;
    private static final String REDIRECT_URI = "http://com.example.applicationv3/callback";
    private static final String CLIENT_ID = "ec59b9a260ab406fbc577be3bc1d285c";
    private static final int REQUEST_CODE = 1337;
    private String token="";
    private Context context;
    private final ScheduledExecutorService tokenrefresh = Executors.newScheduledThreadPool(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_perso);
        context=this;
        musique.clear();
        tokenrefresh.scheduleAtFixedRate(refreshToken(), 0, 1, TimeUnit.HOURS);
        ActivityResultLauncher<Intent> Resultat=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data=result.getData();
                    if (data!=null){
                        Musique newMusic=((Musique) data.getExtras().get("Musique"));
                        if (result.getResultCode()==RESULT_OK){
                            adapterPlaylist.updateData(newMusic,true);
                            musique.add(newMusic);
                        }
                        lire(newMusic);
                    }

                }


        );
        Button btnAdd=findViewById(R.id.boutonRecherchePlaylist);
        btnAdd.setOnClickListener(v -> {
            Intent intent=new Intent(context,SearchSongsActivity.class);
            intent.putExtra("token",token);
            Resultat.launch(intent);
        });


        RecyclerView playlist=findViewById(R.id.recyclerPlaylist);
        adapterPlaylist = new MusiqueAdapter(context, musique);
        playlist.setAdapter(adapterPlaylist);
        playlist.setLayoutManager(new LinearLayoutManager(this));
        adapterPlaylist.updateData(new Musique("Aucune musique","","","",""),true);
    }
    public void setToken(String token) {
        this.token = token;
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
    private Runnable refreshToken() {
        ActivityResultLauncher<Intent> ResultatResfresh = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        AuthorizationResponse response = null;
                            response = AuthorizationClient.getResponse(result.getResultCode(), data);

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


        );
        Runnable runnable = () -> {

            AuthorizationRequest.Builder builder =
                    new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);

            builder.setScopes(new String[]{"playlist-read-private"});
            AuthorizationRequest request = builder.build();

            Intent intent = AuthorizationClient.createLoginActivityIntent((Activity) context, request);
            ResultatResfresh.launch(intent);
        };
        return runnable;
    }
}