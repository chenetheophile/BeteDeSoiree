package com.example.applicationv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class RecettesFragment extends Fragment {

    private ListView ListeRecettes;
    private String[] noms_recettes;
    private String[] detail_recettes;
    private String[] desc_recettes;
    int[] images = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recettes_fragment_layout, container, false);

        ListeRecettes = rootView.findViewById(R.id.ListeRecettes);
        noms_recettes = getResources().getStringArray(R.array.noms_recettes);
        detail_recettes = getResources().getStringArray(R.array.detail_recettes);
        desc_recettes = getResources().getStringArray(R.array.desc_recettes);

        JeuxAdapter jeuxAdapter = new JeuxAdapter(this.getContext(), noms_recettes, detail_recettes, desc_recettes, images);
        ListeRecettes.setAdapter(jeuxAdapter);

        ListeRecettes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "Cliqué : " + String.valueOf(jeuxAdapter.getItem(position)), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                BDD db=new BDD();
                db.chercherDB("recette",noms_recettes[position],"Ingredient",getActivity());

            }
        });
        return rootView;
    }
}
