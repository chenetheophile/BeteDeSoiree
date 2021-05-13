package com.example.applicationv3;

import android.os.Bundle;


import android.view.View;
import android.widget.ImageButton;


import com.google.android.material.tabs.TabLayout;


import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationv3.ui.main.SectionsPagerAdapter;

public class MenuActivity extends AppCompatActivity {
    private TabLayout tabs;
    private String[] noms_cocktails;
    private String[] desc_cocktails;
    private String[] detail_cocktails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.affichage_liste);

        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}