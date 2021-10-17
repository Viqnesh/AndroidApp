package com.example.haribo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationActivity extends AppCompatActivity {
    ImageView PreviousArrow ;
    private ReservationAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        SharedPreferences prefs = getApplicationContext (). getSharedPreferences ("MyPrefs", MODE_PRIVATE);
        int idUser = prefs.getInt ("idUser", 0);
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<Reservation>> call = service.getReservations(3);
        call.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                Log.i("Retrofit" , "Réussi" + response.body());
                recyclerView = findViewById(R.id.recycler_view);
                adapter = new ReservationAdapter(getApplicationContext(),response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ReservationActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                Log.i("Retrofit" , "Echec" + t);

            }
        });


        PreviousArrow = findViewById(R.id.imageView12);

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