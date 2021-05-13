package com.example.applicationv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationv3.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private TabLayout tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ImageButton boutonRech=findViewById(R.id.boutonrecherche);
        boutonRech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rech=new Intent(getApplicationContext(),Recherche.class);
                rech.putExtra("Tout",getIntent().getExtras());
                startActivity(rech);
            }
        });

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}