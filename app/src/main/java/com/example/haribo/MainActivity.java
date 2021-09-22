package com.example.haribo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText pseudo ;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        login = (Button) findViewById(R.id.login);
        pseudo = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginn = pseudo.getText().toString();
                String mdp = password.getText().toString();
                User user = new User(loginn, mdp);
                Call<User> callLogin = service.seConnecter(user);
                callLogin.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        if (response.body() == null) {
                            Log.i("Retrofit" , "User Incorrect" );
                        }
                        else {
                            Log.i("Retrofit" , "Réussi" + response.body());
                            Log.i("Retrofit" , "Réussi" + response.body().getId());
                            Log.i("Retrofit" , "Réussi" + response.body().getEmail());
                            Log.i("Retrofit" , "Réussi" + response.body().getNom());
                            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                            Bundle b = new Bundle();
                            b.putInt("key", response.body().getId()); //Your id
                            intent.putExtras(b); //Put your id to your next Intent
                            startActivity(intent);
                            finish();
                        }


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.i("Retrofit" , "Echec " + t);

                    }
                });


            }
        });
    }
}