package com.example.haribo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("/notifs/{id}")
    Call<List<Notification>> getNotif(@Path("id") int id);

    @GET("/rental/{id}")
    Call<List<Reservation>> getReservations(@Path("id") int id);

    @GET("habitatapp/{id}")
    Call<List<Habitat>> getHabitat(@Path("id") int id);

    @GET("equipement")
    Call<List<Equipement>> getEquipement();

    @GET("commentapp/{id}")
    Call<List<Commentaire>> getCommentaire(@Path("id") int id);

    @GET("photosapp/{id}")
    Call<List<PriseDeVue>> getPhoto(@Path("id") int id);

    @GET("utilisateurs/2")
    Call<User> getUser();

    @POST("addcommentapp")
    Call<Response<String>> saveComment(@Body Commentaire comment);

    @POST("addphotoapp")
    Call<Response<String>> savePhotos(@Body PriseDeVue prisedevue);

    @POST("userlogin")
    Call<User> seConnecter(@Body User user);

    @GET("/display/{id}")
    Call<List<Notification>> getMaxNotif(@Path("id") int id);

}
