package com.example.dashboard.News.NetworkCovid;

import com.example.dashboard.News.ModelCovid.GlobalResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("all")
    Call<GlobalResponse>  globalResponse();
}
