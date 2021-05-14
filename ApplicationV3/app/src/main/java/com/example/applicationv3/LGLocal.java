package com.example.applicationv3;

import android.os.Bundle;

import com.example.applicationv3.ui.main.LGAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationv3.ui.main.SectionsPagerAdapter;

import java.text.ChoiceFormat;
import java.util.ArrayList;

public class LGLocal extends AppCompatActivity {

    public static ArrayList<Boolean> extChoisies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_g_local);

        for (int i = 0; i < getResources().getStringArray(R.array.extensions).length; i++) {
            extChoisies.add(false);
        }

        PagerAdapter lgAdapter = new LGAdapter(getSupportFragmentManager(), 2);

        ViewPager viewPager = findViewById(R.id.view_pager2);
        viewPager.setAdapter(lgAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs2);
        tabLayout.addTab(tabLayout.newTab().setText("Cartes"));
        tabLayout.addTab(tabLayout.newTab().setText("Répartition"));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }
}