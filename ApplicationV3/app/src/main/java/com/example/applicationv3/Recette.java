package com.example.applicationv3;

import java.io.Serializable;

public class Recette implements Serializable {
    private String type;
    private String nom;
    private String lien="";
    private String ingredient;
    private String Description;
    private String tempsPrepa;

    public Recette(String nom, String lien, String ingredient, String description) {
        this.type = "Cocktail";
        this.nom = nom;
        this.lien = lien;
        this.ingredient = ingredient;
        this.Description = description;
    }
    public Recette( String nom, String lien, String ingredient, String description,String temps) {
        this.type = "Recette";
        this.nom = nom;
        this.lien = lien;
        this.ingredient = ingredient;
        this.Description = description;
        this.tempsPrepa=temps;

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
