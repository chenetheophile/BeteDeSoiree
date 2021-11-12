package com.example.applicationv3;

import java.io.Serializable;

public class Musique implements Serializable {
    private String name;
    private String author;
    private String lien;
    private String duree;
    private String lienImage;

    public Musique(String pName, String pAuthor,String pLink,String pDuration,String pLienImage){
        this.name=pName;
        this.author=pAuthor;
        this.lien=pLink;
        this.duree=pDuration;
        this.lienImage=pLienImage;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getLien() {
        return lien;
    }

    public String getDuree() {
        return duree;
    }

    public String getLienImage() {
        return lienImage;
    }

    @Override
    public String toString() {
        return "Musique{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", lien='" + lien + '\'' +
                ", duree='" + duree + '\'' +
                ", lienImage='" + lienImage + '\'' +
                '}';
    }
}

