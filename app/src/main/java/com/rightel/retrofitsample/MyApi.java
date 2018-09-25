package com.rightel.retrofitsample;

import com.rightel.retrofitsample.models.Banner;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {

    @GET("banners")
    Call<Banner> getBanners();


}
