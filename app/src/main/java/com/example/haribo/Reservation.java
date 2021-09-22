package com.example.haribo;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Reservation {


    @SerializedName("id")
    private Integer id;
    @SerializedName("datedebut")
    private String dateDebut;
    @SerializedName("datefin")
    private String dateFin;
    @SerializedName("idHabitatIdLocation")
    private Habitat idHabitatIdLocation;
    @SerializedName("idUserLocataire")
    private User idUserLocataire;


    public Reservation(Integer id, String dateDebut, String dateFin, Habitat idHabitatIdLocation) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idHabitatIdLocation = idHabitatIdLocation;
    }

    public Reservation(Integer id, String dateDebut, String dateFin, Habitat idHabitatIdLocation, User idUserLocataire) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idHabitatIdLocation = idHabitatIdLocation;
        this.idUserLocataire = idUserLocataire;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public Habitat getIdHabitatIdLocation() {
        return idHabitatIdLocation;
    }

    public void setIdHabitatIdLocation(Habitat idHabitatIdLocation) {
        this.idHabitatIdLocation = idHabitatIdLocation;
    }

    public User getIdUserLocataire() {
        return idUserLocataire;
    }

    public void setIdUserLocataire(User idUserLocataire) {
        this.idUserLocataire = idUserLocataire;
    }
}
