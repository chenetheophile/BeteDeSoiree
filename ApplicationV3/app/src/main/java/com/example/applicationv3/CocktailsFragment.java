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
        noms_cocktails = getResources().getStringArray(R.array.noms_cocktails);
        detail_cocktails = getResources().getStringArray(R.array.detail_cocktails);
        desc_cocktails = getResources().getStringArray(R.array.desc_cocktails);

        JeuxAdapter jeuxAdapter = new JeuxAdapter(this.getContext(), noms_cocktails, detail_cocktails, desc_cocktails, images);
        ListeCocktails.setAdapter(jeuxAdapter);

        ListeCocktails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(view, "Cliqué : " + String.valueOf(jeuxAdapter.getItem(position)), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                BDD db=new BDD();
//                db.chercherchampDB("cocktail",noms_cocktails[position],getActivity());
            }
        });
        return rootView;
    }
}
