package com.example.haribo;

import com.google.gson.annotations.SerializedName;

public class Habitat {

    @SerializedName("id")
    private Integer id;
    @SerializedName("nom")
    private String nom;
    @SerializedName("description")
    private String description ;
    @SerializedName("prix")
    private Integer prix ;
    @SerializedName("nbCouchages")
    private Integer nbCouchages ;
    @SerializedName("pays")
    private String pays ;
    @SerializedName("idDomaine")
    private Equipement idDomaine ;
    @SerializedName("url")
    private String url ;

    public Habitat(Integer id, String nom, String description, Integer prix, Integer nbCouchages, String pays, Equipement idDomaine, String url) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.nbCouchages = nbCouchages;
        this.pays = pays;
        this.idDomaine = idDomaine;
        this.url = url;
    }
    public Habitat(Integer id) {
        this.id = id ;
    }
    public Habitat(String nom) {
        this.nom = nom ;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Integer getNbCouchages() {
        return nbCouchages;
    }

    public void setNbCouchages(Integer nbCouchages) {
        this.nbCouchages = nbCouchages;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Equipement getIdDomaine() {
        return idDomaine;
    }

    public void setIdDomaine(Equipement idDomaine) {
        this.idDomaine = idDomaine;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
