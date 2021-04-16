package com.example.applicationv3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ChoixExtensions extends Fragment {

    private ListView listeExtensions;
    private ArrayList<String> nomsExt = new ArrayList<>();
    private ArrayList<String> descExt = new ArrayList<>();
    private ArrayList<Integer> imagesExt = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choix_extensions, container, false);
        Button addExtButton = (Button) rootView.findViewById(R.id.addExtButton);

        listeExtensions = rootView.findViewById(R.id.ListeExtensions);

        String[] nameArray = getResources().getStringArray(R.array.extensions);
        String[] descArray = getResources().getStringArray(R.array.desc_ext);

        for (int i = 0; i < nameArray.length; i++) {
            nomsExt.add(nameArray[i]);
            descExt.add(descArray[i]);
            imagesExt.add(R.drawable.ic_launcher_foreground);
        }

        ChoixAdapter choixAdapter = new ChoixAdapter(this.getContext(), nomsExt, descExt, imagesExt);
        listeExtensions.setAdapter(choixAdapter);

        addExtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < nomsExt.size(); i++) {
                    if (choixAdapter.isChoisi(i)){
                        
                    }
                }
            }
        });

        return rootView;
    }
}