package com.example.haribo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Notification {

        @SerializedName("id")
        private Integer id;
        @SerializedName("titre")
        private String titre;
        @SerializedName("contenu")
        private String contenu;
        @SerializedName("typeHabitat")
        private String typeHabitat;
        @SerializedName("datePublication")
        private Date datePublication;

        public Notification(Integer id, String titre, String contenu, String typeHabitat, Date datePublication) {
            this.id = id ;
            this.titre = titre ;
            this.contenu = contenu ;
            this.typeHabitat = typeHabitat ;
            this.datePublication = datePublication ;
        }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getTypeHabitat() {
        return typeHabitat;
    }

    public void setTypeHabitat(String typeHabitat) {
        this.typeHabitat = typeHabitat;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }
}
