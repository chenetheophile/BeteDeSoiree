package com.example.applicationv3;

import java.io.Serializable;

public class Item implements Serializable {
    private String Type;
    private String Nom;
    private String Lien="";
    private String Etape;
    private String Ingredient;
    private String Description;
    private String TempsPrepa;
    private String Princip;

    public String getPrincip() {
        return Princip;
    }

    private boolean Favori;
    private boolean Alcool;
    private JeuxAdapter adapter;
    public boolean isFavori() {
        return Favori;
    }

    public void setFavori(boolean favori) {
        this.Favori = favori;
    }

    public Item(String nom, String lien, String ingredient, String description,String step,String principal,boolean alcool) {
        this.Type = "Cocktail";
        this.Etape=step;
        this.Favori=false;
        this.Nom = nom;
        this.Alcool=Alcool;
        this.Lien = lien;
        this.Princip=principal;
        this.Ingredient = ingredient;
        this.Description = description;
    }
    public Item(String nom, String lien, String ingredient, String description, String temps,String step,String principal,String test) {
        this.Type = "Recette";
        this.Etape=step;
        this.Nom = nom;
        this.Princip=principal;
        this.Favori=false;
        this.Lien = lien;
        this.Ingredient = ingredient;
        this.Description = description;
        this.TempsPrepa=temps;

    }

    public String getEtape() {
        return Etape;
    }

    public Item(String type, String nom, String joueur, String equipement, boolean a) {//sert pour musique et jeu
        this.Type = type;
        this.Nom = nom;
        this.Ingredient = joueur;
        this.Description = equipement;
    }

    public String getTempsPrepa() {
        return TempsPrepa;
    }

    public boolean isAlcool() {
        return Alcool;
    }

    public JeuxAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(JeuxAdapter adapter) {
        this.adapter = adapter;
    }

    public String getType() {
        return Type;
    }

    public String getNom() {
        return Nom;
    }

    public String getLien() {
        return Lien;
    }

    public String getIngredient() {
        return Ingredient;
    }

    public String getDescription() {
        return Description;
    }
}
