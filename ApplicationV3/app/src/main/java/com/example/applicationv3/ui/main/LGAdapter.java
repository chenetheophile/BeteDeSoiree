package com.example.applicationv3.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.applicationv3.ChoixCartes;
import com.example.applicationv3.ChoixExtensions;
import com.example.applicationv3.Repartition;

public class LGAdapter extends FragmentPagerAdapter {

    int tabCount;

    public LGAdapter(@NonNull FragmentManager fm, int nbTabs) {
        super(fm);
        this.tabCount = nbTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ChoixCartes tab1 = new ChoixCartes();
                return tab1;
            case 1:
                Repartition tab2 = new Repartition();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
