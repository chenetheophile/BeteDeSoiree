package com.example.applicationv3;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private BDD bdd;
    private ProgressBar charg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        charg=findViewById(R.id.chargement);
        charg.setProgress(0);
        bdd=new BDD(charg);
        View par=findViewById(R.id.parametrefrag);
        par.setVisibility(View.GONE);
        Button StartButton =findViewById(R.id.StartButton);
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                charg.setVisibility(View.VISIBLE);
                bdd.getDocument(getApplicationContext());
            }
        });

        ImageButton param=findViewById(R.id.parametre);

        par.setTag(par.getVisibility());
        par.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //check si la la page des param est devant ou non
            @Override
            public void onGlobalLayout() {
                int newVis = par.getVisibility();
                if((int)par.getTag() != newVis)
                {
                    par.setTag(par.getVisibility());
                    if(newVis==View.VISIBLE){
                        StartButton.setVisibility(View.GONE);
                        param.setVisibility(View.GONE);
                    }else{
                        StartButton.setVisibility(View.VISIBLE);
                        param.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        param.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                par.setVisibility(View.VISIBLE);
            }
        });
    }


}