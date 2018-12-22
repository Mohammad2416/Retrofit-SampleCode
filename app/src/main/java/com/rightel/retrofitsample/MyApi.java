package com.rightel.retrofitsample;

import com.rightel.retrofitsample.models.Banner;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

public interface MyApi {

    @GET("banners")
    Observable<Banner> getBanners();
//*** If You Use Just Retrofit you don't need to use Observable, Like this:
//    Call<Banner> getBanners();


}
