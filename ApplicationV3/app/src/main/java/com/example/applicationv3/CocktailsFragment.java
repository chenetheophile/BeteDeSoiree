package com.example.applicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class CocktailsFragment extends Fragment {

    private ListView ListeCocktails;
    private String[] noms_cocktails;
    private String[] detail_cocktails;
    private String[] desc_cocktails;
    int[] images = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.cocktails_fragment_layout, container, false);

        ListeCocktails = rootView.findViewById(R.id.ListeCocktails);
        noms_cocktails = getActivity().getIntent().getExtras().getStringArray("listeNomC");
        detail_cocktails = getActivity().getIntent().getExtras().getStringArray("listeIngreC");
        desc_cocktails = getActivity().getIntent().getExtras().getStringArray("Description");

        JeuxAdapter jeuxAdapter = new JeuxAdapter(this.getContext(), noms_cocktails, detail_cocktails, desc_cocktails, images);
        ListeCocktails.setAdapter(jeuxAdapter);

        ListeCocktails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "Cliqué : " + String.valueOf(jeuxAdapter.getItem(position)), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent cocktail = new Intent(getActivity(), affichage_cocktail.class);
                cocktail.putExtra("boisson",detail_cocktails[position] );
                cocktail.putExtra("Nom", noms_cocktails[position]);
                cocktail.putExtra("lien", getActivity().getIntent().getExtras().getStringArray("lienC")[position]);
                Log.i("test",getActivity().getIntent().getExtras().getStringArray("lienC")[position]);
                getActivity().startActivity(cocktail);
            }
        });
        return rootView;
    }
}
