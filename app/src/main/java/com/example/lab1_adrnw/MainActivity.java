package com.example.lab1_adrnw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.lab1_adrnw.API.PhotoApi;
import com.example.lab1_adrnw.Adapter.PhotoAdapter;
import com.example.lab1_adrnw.Dto.PhotoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<PhotoModel> list;
    private PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new PhotoAdapter(list, this);
        recyclerView.setAdapter(adapter);


    }

    private void loadPhotos(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PhotoApi photoApi = retrofit.create(PhotoApi.class);
        Call<List<PhotoModel>> call = photoApi.getPhotos();

        call.enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                if (response.isSuccessful()){
                    list.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}