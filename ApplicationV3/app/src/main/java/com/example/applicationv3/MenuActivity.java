package com.example.applicationv3;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationv3.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BDD bdd=new BDD();
        setContentView(R.layout.activity_menu);
//        ArrayList<String>Salut=new ArrayList<>();
//        Salut.add("Salut");
//        for(int i=0;i<10000;i++){
//            String lol= String.format("test%s", i);
//
//            try {
//                bdd.creerChamp("test",lol,Salut,Salut);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
        bdd.getDocument();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}