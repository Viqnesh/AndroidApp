package com.example.haribo;

import com.google.gson.annotations.SerializedName;

public class PriseDeVue {

    @SerializedName("id")
    private Integer id;
    @SerializedName("url")
    private String url;
    @SerializedName("IdHabitatIdPrisedevue")
    private Habitat habitat ;

    public PriseDeVue(Integer id, String url, Habitat habitat) {
        this.id = id;
        this.url = url;
        this.habitat = habitat ;
    }

    public  PriseDeVue(String url, Habitat habitat) {
        this.url = url ;
        this.habitat = habitat ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }
}
