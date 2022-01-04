package com.example.applicationv3.SansCategorie;

import android.app.Application;

import com.example.applicationv3.Musique.MusiqueAdapter;

public class donnee extends Application {
    private ItemAdapter adapter1;
    private ItemAdapter adapter2;
    private MusiqueAdapter adapter3;

    public ItemAdapter getAdapterCocktail(){
        return adapter1;
    }
    public void setAdapterCocktail(ItemAdapter adapt){
        this.adapter1=adapt;
    }

    public void setAdapterMusique(MusiqueAdapter adapt){this.adapter3=adapt;}
    public ItemAdapter getAdapterRecette(){
        return adapter2;
    }
    public void setAdapterRecette(ItemAdapter adapt){
        this.adapter2=adapt;
    }
}
