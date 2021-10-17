package com.example.haribo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommenSectionActivity extends AppCompatActivity {
    private CommentaireAdapter adapter;
    private RecyclerView recyclerView;
    ImageView PreviousArrow ;
    TextView contenu ;
    ImageButton envoyer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commen_section);
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        SharedPreferences prefs = getApplicationContext (). getSharedPreferences ("MyPrefs", MODE_PRIVATE);
        int idUser = prefs.getInt ("idUser", 0);
        Bundle b = getIntent().getExtras();
        int idHabitat = b.getInt("habitat");
        User user = new User(idUser);
        Habitat habitat = new Habitat(idHabitat);
        recyclerView = findViewById(R.id.recyclerCommentaire);
        contenu = findViewById(R.id.commentContenu);
        envoyer = findViewById(R.id.envoyer);

        envoyer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String contenuCommentaire = contenu.getText().toString();
                Toast.makeText(getApplicationContext(), String.format(contenuCommentaire), Toast.LENGTH_SHORT).show();
                Commentaire comment = new Commentaire(contenuCommentaire,user,habitat,"titre");
                Call<Response<String>> callAddComment = service.saveComment(comment);
                callAddComment.enqueue(new Callback<Response<String>>() {
                    @Override
                    public void onResponse(Call<Response<String>> call, Response<Response<String>> response) {
                        Log.i("Retrofit" ,"Succès");

                    }

                    @Override
                    public void onFailure(Call<Response<String>> call, Throwable t) {
                        Log.i("Retrofit" ,"Failure");

                    }
                });

            }
        });





        //Afficher les commentaires
        Call<List<Commentaire>> callComment = service.getCommentaire(idHabitat);

        callComment.enqueue(new Callback<List<Commentaire>>() {
            @Override
            public void onResponse(Call<List<Commentaire>> callComment, Response<List<Commentaire>> response) {
                Log.i("Retrofit" , "RéussiCommentaire" + response.body());
                adapter = new CommentaireAdapter(getApplicationContext(), response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CommenSectionActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Commentaire>> callComment, Throwable t) {
                Log.i("Retrofit" , "Echec" + t);
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