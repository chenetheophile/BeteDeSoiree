package com.example.applicationv3.Jeux;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.applicationv3.R;

import java.util.ArrayList;
import java.util.Map;

public class ChoixLG extends AppCompatActivity {

    ArrayList<String> roles;
    ArrayList<String> joueurs;
    ArrayList<Integer> images;
    ArrayList<Boolean> checked;
    Map<String, Integer> img;

    String role;

    ImageView roleImage;
    RecyclerView listeChoix;
    Button validerButton;
    RecyclerView.Adapter LGAdapter;
    RecyclerView.LayoutManager layoutManager;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_l_g);

        img = Map.of("Loup-Garou", R.drawable.loupgarou, "Voyante", R.drawable.voyante, "Petite Fille", R.drawable.petitefille, "Cupidon", R.drawable.cupidon,
                "Chasseur", R.drawable.chasseur, "Sorcière", R.drawable.sorciere, "Voleur", R.drawable.voleur, "Villageois", R.drawable.villageois);

        roles = getIntent().getStringArrayListExtra("roles");
        joueurs = getIntent().getStringArrayListExtra("joueurs");
        images = getIntent().getIntegerArrayListExtra("images");
        role = getIntent().getStringExtra("role");

        checked = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
        }

        checked.add(false);
        LGAdapter = new LGChoixAdapter(roles, joueurs, images, checked,this);
        listeChoix = findViewById(R.id.ListeChoix);
        validerButton = findViewById(R.id.validerChoixButton);
        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < roles.size(); i++) {
                    if (LGAdapter.getItemViewType(i) == 1) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("result", joueurs.get(i));
                        resultIntent.putExtra("role", role);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                        return;
                    }
                }
                Toast.makeText(ChoixLG.this, "Sélectionnez un joueur.", Toast.LENGTH_LONG).show();
            }
        });

        roleImage = findViewById(R.id.roleChoixImageView);
        roleImage.setImageResource(img.get(role));

        layoutManager = new LinearLayoutManager(this);
        listeChoix.setLayoutManager(layoutManager);
        listeChoix.setAdapter(LGAdapter);
    }
}