package com.example.applicationv3;

public class Musique {
    private String Titre;
    private String Auteur;
    private String ID_Titre;
    private Integer Img;

    public String getTitre() {
        return Titre;
    }

    public String getAuteur() {
        return Auteur;
    }

    public String getID_Titre() {
        return ID_Titre;
    }

    public Integer getImg() {
        return Img;
    }

    public Musique(String pTitre, String pAuteur, String pID_Titre, Integer pImg){
        this.Auteur=pAuteur;
        this.ID_Titre=pID_Titre;
        this.Img=pImg;
        this.Titre=pTitre;
    }
}
