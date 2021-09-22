package com.example.haribo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HabitatActivity extends AppCompatActivity {
    private EquipementAdapter adapter;
    private RecyclerView recyclerView;
    private CommentaireAdapter adapter2;
    private RecyclerView recyclerView2;
    private PriseDeVueAdapter adapter3;
    private RecyclerView recyclerView3;
    ImageView pointsImg ;
    ImageView PreviousArrow;
    Integer habitatId ;
    FloatingActionButton addEdit ;
    FloatingActionButton addPhoto ;
    FloatingActionButton addPen ;
    TextView txtNom;
    TextView txtDescription;
    TextView txtPrice;
    TextView txtPays;
    ImageView imageH ;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitat);
        txtNom = findViewById(R.id.nom);
        txtDescription = findViewById(R.id.description);
        txtPrice = findViewById(R.id.prix);
        txtPays = findViewById(R.id.pays);
        imageH = findViewById(R.id.imgHabitat);
        recyclerView = findViewById(R.id.recyclerViewEq);
        recyclerView2 = findViewById(R.id.commene);
        recyclerView3 = findViewById(R.id.commentairespace);
        addEdit = findViewById(R.id.addEdit);
        addPen = findViewById(R.id.editButton);
        addPhoto = findViewById(R.id.photoEdit);
         Bundle b = getIntent().getExtras();
         int value = 4; // or other values
         if(b != null)
         value = b.getInt("key");
         Log.i("Parameter" ,"Habitat Id" + value);
        ArrayAdapter<CharSequence> adapterS = ArrayAdapter.createFromResource(this, R.array.spinnerItems, android.R.layout.simple_spinner_item);
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<Habitat>> call = service.getHabitat(value);
        Call<List<Equipement>> callEquip = service.getEquipement();
        Call<List<Commentaire>> callComment = service.getCommentaire(value);
        Call<List<PriseDeVue>> callPhoto = service.getPhoto(value);
        call.enqueue(new Callback<List<Habitat>>() {
            @Override
            public void onResponse(Call<List<Habitat>> call, Response<List<Habitat>> response) {
                Log.i("Retrofit" , "Réussi" + response.body().get(0).getUrl());
                txtNom.setText(response.body().get(0).getNom());
                txtDescription.setText(response.body().get(0).getDescription());
                habitatId = response.body().get(0).getId();
                addPen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle b = new Bundle();
                        Intent intent = new Intent(getApplicationContext(), CommenSectionActivity.class);
                        b.putInt("habitat", finalValue); //Your id
                        b.putInt("idUser", finalIdUser); //Your id
                        intent.putExtras(b); //Put your id to your next Intent
                        startActivity(intent);
                        finish();

                    }
                });
                Picasso.get().load("https://www.insoolite.fr/wp-content/uploads/Bulle-id-1713-1.jpeg").into(imageH);
                callEquip.enqueue(new Callback<List<Equipement>>() {
                    @Override
                    public void onResponse(Call<List<Equipement>> callEquip, Response<List<Equipement>> response) {
                        Log.i("Retrofit" , "Réussi" + response.body());
                        adapter = new EquipementAdapter(getApplicationContext(), response.body());
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HabitatActivity.this, LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<Equipement>> callEquip, Throwable t) {
                        Log.i("Retrofit" , "Echec" + t);
                    }
                });
                callComment.enqueue(new Callback<List<Commentaire>>() {
                    @Override
                    public void onResponse(Call<List<Commentaire>> callComment, Response<List<Commentaire>> response) {
                        Log.i("Retrofit" , "RéussiCommentaire" + response.body());
                        adapter2 = new CommentaireAdapter(getApplicationContext(), response.body());
                        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(HabitatActivity.this);
                        recyclerView2.setLayoutManager(layoutManager2);
                        recyclerView2.setAdapter(adapter2);
                    }

                    @Override
                    public void onFailure(Call<List<Commentaire>> callComment, Throwable t) {
                        Log.i("Retrofit" , "Echec" + t);
                    }
                });
                callPhoto.enqueue(new Callback<List<PriseDeVue>>() {
                    @Override
                    public void onResponse(Call<List<PriseDeVue>> callPhoto, Response<List<PriseDeVue>> response) {
                        Log.i("Retrofit" , "Réussi Photo" + response.body());
                        adapter3 = new PriseDeVueAdapter(getApplicationContext(), response.body());
                        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(HabitatActivity.this);
                        recyclerView3.setLayoutManager(layoutManager3);
                        recyclerView3.setAdapter(adapter3);
                    }

                    @Override
                    public void onFailure(Call<List<PriseDeVue>> callPhoto, Throwable t) {
                        Log.i("Retrofit" , "EchecPhoto" + t);

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Habitat>> call, Throwable t) {
                Log.i("Retrofit" , "Echec" + t);

            }
        });


         addEdit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 addPhoto.setVisibility(view.VISIBLE);
                 addPen.setVisibility(view.VISIBLE);

             }
         });

         addPhoto.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Bundle b = new Bundle();
                 Intent intent = new Intent(getApplicationContext(), PhotoSectionActivity.class);
                 b.putInt("habitat", finalValue); //Your id
                 b.putInt("idUser" , finalIdUser);
                 intent.putExtras(b); //Put your id to your next Intent
                 startActivity(intent);
                 finish();

             }
         });

         PreviousArrow = findViewById(R.id.imageView12);
         BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
         navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
         PreviousArrow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();

             }
         });
     }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_android:
                    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                    startActivity(intent);
                    break;
                case R.id.action_logo:
                    Intent intent2 = new Intent(getApplicationContext(), ReservationActivity.class);
                    startActivity(intent2);
                    break;

                default:
                    Intent intent3 = new Intent(getApplicationContext(), NotificationActivity.class);
                    startActivity(intent3);
                    break;
            }
            return false;
        }

    };



}