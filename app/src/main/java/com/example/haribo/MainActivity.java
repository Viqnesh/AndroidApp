package com.example.haribo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static final String MyPREFERENCES = "MyPrefs" ;
    public SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        login = (Button) findViewById(R.id.login);
        pseudo = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
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
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putInt("idUser", response.body().getId());
                            editor.putString("Picture", response.body().getUrl());
                            editor.commit();
                            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
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