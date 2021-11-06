package com.example.applicationv3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

public class SearchSongsActivity extends AppCompatActivity {
    private static final String REDIRECT_URI = "http://com.example.applicationv3/callback";
    private static final String CLIENT_ID="ec59b9a260ab406fbc577be3bc1d285c";
    private static final int REQUEST_CODE = 1337;
    private Activity act;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_songs);
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
                AuthorizationRequest.Builder builder =
                        new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);

                builder.setScopes(new String[]{"playlist-read-private"});
                AuthorizationRequest request = builder.build();
                Intent intent = AuthorizationClient.createLoginActivityIntent(new Activity(), request);
                intent.putExtra("char",titre.getText().toString().toLowerCase().trim());
                startActivityForResult(intent, REQUEST_CODE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



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
                    Log.i("token",response.toString());

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
}