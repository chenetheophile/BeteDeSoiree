package com.example.applicationv3.Musique;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationv3.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;


public class MusiqueFragment extends Fragment {

    private RecyclerView ListeMusique;
    private ArrayList<String> Playlist=new ArrayList<>(Arrays.asList("Chill","Indie","Dance/Electronique","Rock","Jazz","Classique"));
    private ArrayList<String> Desc=new ArrayList<>(Arrays.asList("Pour des soirées calmes","Des découvertes en perspectives","De quoi s'ambiancer","Rock","Pour des nouvelles vibes ","Parce que le retour au source nous fait du bien"));
    private ArrayList<String> lien=new ArrayList<>(Arrays.asList("playlist:37i9dQZF1DXdCsscAsbRNz","playlist:37i9dQZF1DX924zU1IARaD",
            "playlist:37i9dQZF1DXaXB8fQg7xif","playlist:37i9dQZF1DWXTHBOfJ8aI7",
            "playlist:37i9dQZF1DXbITWG1ZJKYt","playlist:37i9dQZF1DWV0gynK7G6pD"));
    private ArrayList<Musique> musique=new ArrayList<>();

    private static final int REQUEST_CODE = 1337;
    private static final String REDIRECT_URI = "http://com.example.applicationv3/callback";
    private static final String CLIENT_ID="ec59b9a260ab406fbc577be3bc1d285c";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FileInputStream fis;
        String contenu="";
        ActivityResultLauncher<Intent> Resultat=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        AuthorizationResponse response = AuthorizationClient.getResponse(result.getResultCode(), data);

                        switch (response.getType()) {
                            // Response was successful and contains auth token
                            case TOKEN:
                                sauver("Spotify");
                                getActivity().recreate();
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
        try {
            fis = getActivity().openFileInput("param");
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                contenu+=line;
                line = reader.readLine();
            }

        } catch (IOException e) {
                sauver("None");
                contenu="None";
        }
        if(contenu.equalsIgnoreCase("None")){
            View rootView = inflater.inflate(R.layout.fragment_choix_stream, container, false);
            ListeMusique = rootView.findViewById(R.id.ListeMusique);


            Button spotify = rootView.findViewById(R.id.SpotifyButton);
            Button Deezer = rootView.findViewById(R.id.DeezerButton);
            Button Ytb = rootView.findViewById(R.id.YoutubeButton);

            spotify.setOnClickListener(v -> {
                AuthorizationRequest.Builder builder =
                        new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI);

                builder.setScopes(new String[]{"playlist-read-private"});
                AuthorizationRequest request = builder.build();
                Intent intent = AuthorizationClient.createLoginActivityIntent(getActivity(), request);
                Resultat.launch(intent);

            });
            Deezer.setOnClickListener(v -> ConnexionDeezer());
            Ytb.setOnClickListener(v -> ConnexionYtb());

            return rootView;
        }else{
            View rootView = inflater.inflate(R.layout.musique_fragment_layout, container, false);
            ListeMusique = rootView.findViewById(R.id.ListeMusique);
            FloatingActionButton newPlaylist=rootView.findViewById(R.id.NewPlaylistbutton);
            newPlaylist.setOnClickListener(v -> {
                Intent intent=new Intent(getActivity(),PlaylistPerso.class);
                intent.putExtra("lecture",false);
                startActivity(intent);
            });
            for(int i=0;i<Playlist.size();i++){
                musique.add(new Musique(Playlist.get(i),Desc.get(i),lien.get(i),"",""));
            }
            MusiqueAdapter musiqueAdapter = new MusiqueAdapter(this.getContext(), musique);
            ListeMusique.setAdapter(musiqueAdapter);
            ListeMusique.setLayoutManager(new LinearLayoutManager(getContext()));
            return rootView;
        }

    }

    private void sauver(String param) {
        try {
            FileOutputStream outputStream;
            String filename="param";
            outputStream = getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(param.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    private void ConnexionDeezer() {


    }
    private void ConnexionYtb() {


    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//        // Check if result comes from the correct activity
//        if (requestCode == REQUEST_CODE) {
//            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode, intent);
//            Log.i("spot",response.getType().toString());
//            switch (response.getType()) {
//                // Response was successful and contains auth token
//                case TOKEN:
//                    Log.i("spot","connect");
//                    sauver("Spotify");
//                    getActivity().recreate();
//                    break;
//
//                // Auth flow returned an error
//                case ERROR:
//                    Log.e("erreur Spotify", response.getError());
//                    // Handle error response
//                    break;
//
//                // Most likely auth flow was cancelled
//                default:
//                    // Handle other cases
//            }
//        }
//    }
}
