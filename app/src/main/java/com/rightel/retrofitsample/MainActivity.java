package com.rightel.retrofitsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rightel.retrofitsample.models.Banner;
import com.rightel.retrofitsample.models.Content;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

   private static Retrofit retrofit;
   private static final String BASE_URL = "https://rbtapp.rightel.ir:82/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        retrofit = new Retrofit.Builder()
//                .baseUrl("https://rbtapp.rightel.ir:82/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        MyApi myApi = getRetrofitInstance().create(MyApi.class);
        myApi.getBanners().enqueue(new Callback<Banner>() {
            @Override
            public void onResponse(Call<Banner> call, Response<Banner> response) {
                Banner banner = response.body();
                List<Content> banners = banner.getContent();
                banners.get(1).getName();

            }

            @Override
            public void onFailure(Call<Banner> call, Throwable t) {

            }
        });

    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
