package com.example.applicationv3.SansCategorie;

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
    private ItemAdapter adapter;
    public boolean isFavori() {
        return Favori;
    }

    public void setFavori(boolean favori) {
        this.Favori = favori;
    }

    public String getEtape() {
        return Etape;
    }

    public Item(String type, String nom, String joueur, String equipement, boolean a) {
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

    public ItemAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ItemAdapter adapter) {
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

    public void setLien(String lien) {
        Lien = lien;
    }
}
