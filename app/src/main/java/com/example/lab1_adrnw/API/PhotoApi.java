package com.example.lab1_adrnw.API;

import com.example.lab1_adrnw.Dto.PhotoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoApi {
    @GET ("photos")
    Call<List<PhotoModel>> getPhotos();
}
