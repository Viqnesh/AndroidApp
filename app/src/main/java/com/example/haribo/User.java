package com.example.haribo;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    private Integer id ;
    @SerializedName("email")
    private String email;
    @SerializedName("nom")
    private String nom;
    @SerializedName("prenom")
    private String prenom;
    @SerializedName("password")
    private String password;

    public User(Integer id, String email, String nom, String prenom) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    }
    public User(Integer id) {
        this.id = id ;
    }
    public User(String email) {
        this.email = email ;
    }
    public User(String email, String password) {
        this.email = email ;
        this.password = password;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
