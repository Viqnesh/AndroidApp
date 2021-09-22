package com.example.haribo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoSectionActivity extends AppCompatActivity {
    private AddPhotoAdapter adapter;
    final String uploadFilePath =  Environment.getExternalStorageDirectory().getPath();
    private RecyclerView recyclerView;
    ImageView PreviousArrow ;
    Button envoyer;
    public static final int GET_FROM_GALLERY = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_section);
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        recyclerView = findViewById(R.id.recyclerCommentaire);
        Bundle b = getIntent().getExtras();
        int idHabitat = b.getInt("habitat");
        int idUser = b.getInt("idUser");
        Call<List<PriseDeVue>> callComment = service.getPhoto(idHabitat);
        Habitat habitat = new Habitat(idHabitat);

        recyclerView = findViewById(R.id.recyclerCommentaire);
        envoyer = findViewById(R.id.buttonEnvoyer);

        envoyer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);

            }
        });
        callComment.enqueue(new Callback<List<PriseDeVue>>() {
            @Override
            public void onResponse(Call<List<PriseDeVue>> callComment, Response<List<PriseDeVue>> response) {
                Log.i("Retrofit" , "RéussiCommentaire" + response.body());
                adapter = new AddPhotoAdapter(getApplicationContext(), response.body());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PhotoSectionActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<PriseDeVue>> callComment, Throwable t) {
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


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = getIntent().getExtras();
        int idHabitat = b.getInt("habitat");
        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Habitat habitat = new Habitat(idHabitat);
        try {
            switch (requestCode) {

                case GET_FROM_GALLERY :
                    if (resultCode == Activity.RESULT_OK) {
                        Uri selectedImageUri = data.getData();
                        String selectedImagePath = getPath(selectedImageUri);
                        Bitmap bmp= BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageUri));
                        String bytesImageURI = getStringFromBitmap(bmp);
                        PriseDeVue prisedevue = new PriseDeVue(bytesImageURI,habitat);
                        Call<Response<String>> callAddPhoto = service.savePhotos(prisedevue);
                        callAddPhoto.enqueue(new Callback<Response<String>>() {
                            @Override
                            public void onResponse(Call<Response<String>> call, Response<Response<String>> response) {
                                Log.i("Retrofit", "Photo Choisie ok " + bytesImageURI);
                                Toast.makeText(PhotoSectionActivity.this,"Image uploaded",Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<Response<String>> call, Throwable t) {
                                Log.i("Retrofit", "Echec");

                            }
                        });
                        break;
                    } else if (resultCode == Activity.RESULT_CANCELED) {
                        Log.i("Photo Chooser", "Selecting picture cancelled");
                    }
                    break;
            }
        } catch (Exception e) {
            Log.i("Activity Result", "Exception in onActivityResult : " + e.getMessage());
        }
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    /*
     * This functions converts Bitmap picture to a string which can be
     * JSONified.
     * */
    private String getStringFromBitmap(Bitmap bitmapPicture) {
        final int COMPRESSION_QUALITY = 100;
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
                byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }
}