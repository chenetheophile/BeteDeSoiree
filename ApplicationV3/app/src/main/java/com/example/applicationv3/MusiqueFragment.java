package com.example.applicationv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;


public class MusiqueFragment extends Fragment {

    private RecyclerView ListeMusique;
    private ArrayList<String> Playlist=new ArrayList<>(Arrays.asList("Chill","Indie","Dance/Electronique","Rock","Jazz","Classique"));
    private ArrayList<String> Desc=new ArrayList<>(Arrays.asList("Pour des soirées calmes","Des découvertes en perspectives","De quoi s'ambiancer","Rock","Pour des nouvelles vibes ","Parce que le retour au source nous fait du bien"));
    private ArrayList<String> Detail=new ArrayList<>(Arrays.asList("playlist:37i9dQZF1DXdCsscAsbRNz","playlist:37i9dQZF1DX924zU1IARaD",
            "playlist:37i9dQZF1DXaXB8fQg7xif","playlist:37i9dQZF1DWXTHBOfJ8aI7",
            "playlist:37i9dQZF1DXbITWG1ZJKYt","playlist:37i9dQZF1DWV0gynK7G6pD"));
    private ArrayList<Item> musique=new ArrayList<>();
    private ArrayList<Integer> images = new ArrayList<>(Arrays.asList(R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground));

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.musique_fragment_layout, container, false);

        ListeMusique = rootView.findViewById(R.id.ListeMusique);

        for(int i=0;i<Playlist.size();i++){
            musique.add(new Item("Musique",Playlist.get(i),Detail.get(i),Desc.get(i),true));
        }
        JeuxAdapter jeuxAdapter = new JeuxAdapter(this.getContext(), musique, images);
        ListeMusique.setAdapter(jeuxAdapter);
        ListeMusique.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }
}
