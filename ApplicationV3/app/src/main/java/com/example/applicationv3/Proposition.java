package com.example.applicationv3;


import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseUser;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Proposition extends AppCompatActivity {
    private  EditText nomRecettePro,Desc;
    private ListeAdapter listeIngrAdapter;
    private ListeAdapter listeEtapeAdapter;
    private TimePicker horloge;
    private  Bitmap photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition);

        horloge=findViewById(R.id.horloge);
        horloge.setIs24HourView(true);

        TextView text=findViewById(R.id.Type);
        text.setText(getIntent().getExtras().getString("type"));

        Spinner nbIngr=findViewById(R.id.nbIngr);
        ArrayAdapter<Integer> adapterIngr =new ArrayAdapter<Integer>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item){
        };
        for(int i=0;i<50;i++){
            adapterIngr.add(i);
        }
        nbIngr.setAdapter(adapterIngr);
        nbIngr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                onItemSelectedHandler(parent,view,position,id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner nbEtape=findViewById(R.id.nbEtape);
        ArrayAdapter<Integer> adapterEtape =new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item);
        for(int i=0;i<50;i++){
            adapterEtape.add(i);
        }
        nbEtape.setAdapter(adapterEtape);
        nbEtape.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                onItemSelectedHandler(parent,view,position,id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FirebaseUser usr= (FirebaseUser) getIntent().getExtras().get("User");

        nomRecettePro=findViewById(R.id.NomRecettePropo);
        Desc=findViewById(R.id.DescriptionPropo);



        Button envoi=findViewById(R.id.envoyer);
        envoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendProp();
            }
        });
    }

    private void onItemSelectedHandler(AdapterView<?> parent, View view, int position, long id) {
        Spinner ing=findViewById(R.id.nbIngr);
        if(parent.equals(ing)){
            RecyclerView recyclerIngr=findViewById(R.id.recyclerIngr);
            ArrayList<String> listeIngr=new ArrayList<>();

            for(int i=0;i<position;i++){
                listeIngr.add("");
            }

            listeIngrAdapter=new ListeAdapter(getApplicationContext(),listeIngr);
            recyclerIngr.setAdapter(listeIngrAdapter);
            recyclerIngr.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }else{
            RecyclerView recyclerEtape=findViewById(R.id.recyclerEtape);
            ArrayList<String> listeEtape=new ArrayList<>();

            for(int i=0;i<position;i++){
                listeEtape.add("");
            }

            listeEtapeAdapter=new ListeAdapter(getApplicationContext(),listeEtape);
            recyclerEtape.setAdapter(listeEtapeAdapter);
            recyclerEtape.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }

    }

    private void sendProp(){//envoi la proposition par mail
        String Etape="";
        String Ingr="";
        ArrayList<String> listeIngr=listeIngrAdapter.getListe();
        ArrayList<String> listeEtape=listeEtapeAdapter.getListe();
        for (int i=0;i<listeIngr.size();i++){
            Ingr+=listeIngr.get(i)+"@";
        }
        for (int i=0;i<listeEtape.size();i++){
            
            Etape+=listeEtape.get(i)+"@";
        }
        new BDD().addPropo(getApplicationContext(),nomRecettePro.getText().toString(),Ingr,Desc.getText().toString(),Etape,getIntent().getExtras().getString("type"),horloge.getMinute()+horloge.getHour()*60);
        finish();
        Toast.makeText(getApplicationContext(),"Proposition envoyé",Toast.LENGTH_LONG).show();
    }
}