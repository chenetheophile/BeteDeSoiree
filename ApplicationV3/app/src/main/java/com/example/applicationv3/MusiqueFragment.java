package com.example.applicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MusiqueFragment extends Fragment {

    private RecyclerView ListeMusique;
    private String[] noms_musique;
    private String[] detail_musique;
    private String[] desc_musique;
    int[] images = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.musique_fragment_layout, container, false);

        ListeMusique = rootView.findViewById(R.id.ListeMusique);
        noms_musique = getResources().getStringArray(R.array.noms_musique);
        detail_musique = getResources().getStringArray(R.array.detail_musique);
        desc_musique = getResources().getStringArray(R.array.desc_musique);

        JeuxAdapter jeuxAdapter = new JeuxAdapter(this.getContext(), noms_musique, detail_musique, desc_musique, images);
        ListeMusique.setAdapter(jeuxAdapter);
        ListeMusique.setLayoutManager(new LinearLayoutManager(getContext()));
//        ListeMusique.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent=new Intent(getActivity(),testSpot.class);
//                startActivity(intent);
//            }
//        });
        return rootView;
    }
}
