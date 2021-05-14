package com.example.applicationv3.ui.main;

import android.content.Context;
import android.util.Log;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.applicationv3.JeuxFragment;
import com.example.applicationv3.CocktailsFragment;
import com.example.applicationv3.RecettesFragment;
import com.example.applicationv3.MusiqueFragment;
import com.example.applicationv3.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        Log.i("pos", String.valueOf(position));
        switch (position){
            case 0:
                fragment = new JeuxFragment();
                break;
            case 1:
                fragment = new CocktailsFragment();
                break;
            case 2:
                fragment = new RecettesFragment();
                break;
            case 3:
                fragment = new MusiqueFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }


}