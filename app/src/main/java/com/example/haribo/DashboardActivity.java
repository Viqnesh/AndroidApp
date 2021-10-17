package com.example.haribo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    TextView NotifLink ;
    TextView ReserLink ;
    ImageView profilPic ;
    private NotifAdapter adapter;
    private RecyclerView recyclerView;
    private ReservationAdapter adapter2;
    private RecyclerView recyclerView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Bundle b = getIntent().getExtras();
        SharedPreferences prefs = getApplicationContext (). getSharedPreferences ("MyPrefs", MODE_PRIVATE);
        int idUser = prefs.getInt ("idUser", 0);
        String profileImg = prefs.getString("Picture" , "dfd");
        Log.i("Shared" , "Test" + idUser);
        NotifLink =  findViewById(R.id.textView17);
        ReserLink = findViewById(R.id.textView6);
        profilPic = findViewById(R.id.imageView2);
        InputStream stream = new ByteArrayInputStream(profileImg.getBytes(StandardCharsets.UTF_8));
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        Drawable d = new BitmapDrawable(getResources(), bitmap);
        profilPic.setImageBitmap(bitmap);
        profilPic.setImageDrawable(d);
        recyclerView = findViewById(R.id.recylerNotif);
        recyclerView2 = findViewById(R.id.recylerReser);
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<Notification>> call = service.getMaxNotif(idUser);
        call.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response <List<Notification>> response) {
                Log.i("Retrofit" , "Réussi" + response.body());
                recyclerView = findViewById(R.id.recylerNotif);
                adapter = new NotifAdapter(getApplicationContext(),response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DashboardActivity.this,LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                Log.i("Retrofit" , "Echec" + t);

            }
        });
        Call<List<Reservation>> call2 = service.getReservations(3);
        call2.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                Log.i("Retrofit" , "Réussi" + response.body());
                recyclerView2 = findViewById(R.id.recylerReser);
                adapter2 = new ReservationAdapter(getApplicationContext(),response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DashboardActivity.this);
                recyclerView2.setLayoutManager(layoutManager);
                recyclerView2.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                Log.i("Retrofit" , "Echec" + t);

            }
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.activity_main_bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        NotifLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        ReserLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReservationActivity.class);
                startActivity(intent);
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