package com.example.applicationv3;

import java.io.Serializable;

public class Item implements Serializable {
    private String type;
    private String nom;
    private String lien="";
    private Integer image;
    private String ingredient;
    private String Description;
    private String tempsPrepa;
    private boolean favori;
    private JeuxAdapter adapter;
    public boolean isFavori() {
        return favori;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
    }

    public Item(boolean fav, String nom, String lien, String ingredient, String description) {
        this.type = "Cocktail";
        this.favori=fav;
        this.nom = nom;
        this.lien = lien;
        this.ingredient = ingredient;
        this.Description = description;
    }
    public Item(boolean fav,String nom, String lien, String ingredient, String description, String temps) {
        this.type = "Recette";
        this.nom = nom;
        this.favori=fav;
        this.lien = lien;
        this.ingredient = ingredient;
        this.Description = description;
        this.tempsPrepa=temps;

    }
    public Item(String type, String nom, String joueur, String equipement,boolean a) {//sert pour musique et jeu
        this.type = type;
        this.nom = nom;
        this.ingredient = joueur;
        this.Description = equipement;
    }

    public JeuxAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(JeuxAdapter adapter) {
        this.adapter = adapter;
    }

    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public String getLien() {
        return lien;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getDescription() {
        return Description;
    }
}
