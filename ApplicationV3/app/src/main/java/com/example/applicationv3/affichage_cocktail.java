package com.example.applicationv3;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class affichage_cocktail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_cocktail);

        TextView listeIngr=findViewById(R.id.listeIngredient);
        listeIngr.setText(getIntent().getExtras().getString("boisson"));

        TextView plat=findViewById(R.id.NomCocktail);
        plat.setText(getIntent().getExtras().getString("Nom"));

        ImageView imgCocktail=findViewById(R.id.img_cocktail);
        Picasso.get().load(getIntent().getExtras().getString("lien")).into(imgCocktail);


    }
}