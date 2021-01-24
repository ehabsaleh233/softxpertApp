package com.softxpert.cars.data.network;

import com.softxpert.cars.data.entity.CarsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("cars")
    Call<CarsModel> getCarsList(@Query("page") int page );
}
