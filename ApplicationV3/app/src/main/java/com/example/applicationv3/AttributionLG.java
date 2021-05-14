package com.example.applicationv3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AttributionLG extends AppCompatActivity {

    private RecyclerView ListeRoles;
    private ArrayList<String> nomsJoueurs = new ArrayList<>();
    private Map <String, Integer> img;
    public ArrayList<Integer> imagesCartes = new ArrayList<>();
    public ArrayList<String> nomsRoles = new ArrayList<>();

    private Button validerButton;
    private FloatingActionButton infoButton;
    private RecyclerView.Adapter rolesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribution_l_g);

        img = Map.of("Loup-Garou", R.drawable.loupgarou, "Voyante", R.drawable.voyante, "Petite Fille", R.drawable.petitefille, "Cupidon", R.drawable.cupidon,
        "Chasseur", R.drawable.chasseur, "Sorcière", R.drawable.sorciere, "Voleur", R.drawable.voleur, "Villageois", R.drawable.villageois);

        for (int i = 0; i < ChoixCarteAdapter.numCartes.size(); i++) {
            for (int j = 0; j < ChoixCarteAdapter.numCartes.get((String) ChoixCarteAdapter.numCartes.keySet().toArray()[i]); j++) {
                nomsRoles.add((String) ChoixCarteAdapter.numCartes.keySet().toArray()[i]);
                imagesCartes.add(img.get((String) ChoixCarteAdapter.numCartes.keySet().toArray()[i]));
            }
        }
        System.out.println(nomsRoles.toString());

        nomsJoueurs = getIntent().getStringArrayListExtra("Joueurs");

        infoButton = findViewById(R.id.infoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InfoAttrib.class));
            }
        });
        validerButton = findViewById(R.id.validerButton);
        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "valider", Snackbar.LENGTH_SHORT).show();
            }
        });
        ListeRoles = findViewById(R.id.ListeRoles);

        rolesAdapter = new RoleAdapter(this, nomsJoueurs, imagesCartes, nomsRoles);

        layoutManager = new LinearLayoutManager(this);
        ListeRoles.setLayoutManager(layoutManager);
        ListeRoles.setAdapter(rolesAdapter);
    }
}
