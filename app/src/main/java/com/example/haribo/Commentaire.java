package com.example.haribo;

import com.google.gson.annotations.SerializedName;

public class Commentaire {

    @SerializedName("id")
    private Integer id ;
    @SerializedName("idUser")
    private User user ;
    @SerializedName("idHabitatIdCommentaire")
    private Habitat habitat;
    @SerializedName("contenu")
    private String contenu ;
    @SerializedName("titre")
    private String titre ;

    public Commentaire(Integer id, User user, String contenu) {
        this.id = id;
        this.user = user;
        this.contenu = contenu;
    }

    public Commentaire(String contenu, User user, Habitat habitat, String titre) {
        this.user = user ;
        this.contenu = contenu ;
        this.habitat = habitat ;
        this.titre = titre ;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
