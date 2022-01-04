package com.example.applicationv3.SansCategorie;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.applicationv3.Jeux.SectionsPagerAdapter;
import com.example.applicationv3.R;
import com.google.android.material.tabs.TabLayout;

public class MenuActivity extends AppCompatActivity {
    private TabLayout tabs;
    private FragmentManager fragmentManager=getFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = findViewById(R.id.affichage_liste);
        viewPager.setOffscreenPageLimit(3);

        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }


}