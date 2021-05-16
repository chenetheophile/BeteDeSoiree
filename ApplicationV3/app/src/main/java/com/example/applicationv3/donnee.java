package com.example.applicationv3;

import android.app.Application;

public class donnee extends Application {
    private JeuxAdapter adapter1;
    private JeuxAdapter adapter2;

    public JeuxAdapter getAdapterCocktail(){
        return adapter1;
    }
    public void setAdapterCocktail(JeuxAdapter adapt){
        this.adapter1=adapt;
    }
    public JeuxAdapter getAdapterRecette(){
        return adapter2;
    }
    public void setAdapterRecette(JeuxAdapter adapt){
        this.adapter2=adapt;
    }
}
