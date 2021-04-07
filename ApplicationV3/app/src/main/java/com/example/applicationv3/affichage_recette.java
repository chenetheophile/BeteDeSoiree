package com.example.applicationv3;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class affichage_recette extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_recette);

        TextView txt=findViewById(R.id.listeIngredient);
        txt.setText(getIntent().getExtras().getString("nourriture"));

        TextView plat=findViewById(R.id.NomRecette);
        plat.setText(getIntent().getExtras().getString("Nom"));

        RatingBar fav=findViewById(R.id.fav);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fav.getNumStars()==1){
                    sauver(plat.getText().toString());
                }
            }
        });
        ImageView imgRecette=findViewById(R.id.img_recette);
        Picasso.get().load(getIntent().getExtras().getString("lien")).into(imgRecette);
    }
    private void sauver(String recette){

    }
}