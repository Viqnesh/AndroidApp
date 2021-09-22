package com.example.haribo;

import com.google.gson.annotations.SerializedName;

public class Equipement {

    @SerializedName("id")
    private Integer id;
    @SerializedName("nom")
    private String nom;
    @SerializedName("icon")
    private String icon ;

    public Equipement(Integer id, String nom, String icon) {
        this.id = id;
        this.nom = nom;
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


}
