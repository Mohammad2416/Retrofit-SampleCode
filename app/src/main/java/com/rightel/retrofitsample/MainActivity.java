package com.rightel.retrofitsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.rightel.retrofitsample.models.Banner;
import com.rightel.retrofitsample.models.Content;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://rbtapp.rightel.ir:82/";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textViewMain);

//********* To Create a instance of Retrofit in simple way you can writhe below code but it is not good way

//        retrofit = new Retrofit.Builder()
//                .baseUrl("https://rbtapp.rightel.ir:82/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//*********
//        MyApi myApi = getRetrofitInstance().create(MyApi.class);
//        myApi.getBanners().enqueue(new Callback<Banner>() {
//******** 
//          getRetrofitInstance().create(MyApi.class).getBanners().enqueue(new Callback<Banner>() {
//            @Override
//            public void onResponse(Call<Banner> call, Response<Banner> response) {
//                Banner banner = response.body();
//                List<Content> banners = banner.getContent();
//                banners.get(1).getName();
//            }
//
//            @Override
//            public void onFailure(Call<Banner> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });

        getData();
    }

// a better way to create an instance of Retrofit
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    private void getData() {
//****** You can get data with this way:

//        getRetrofitInstance().create(MyApi.class).getBanners().subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Banner>() {
//                    @Override
//                    public void onCompleted() {
//                        Toast.makeText(MainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//
//                    }
//
//                    @Override
//                    public void onNext(Banner data) {
//                        Banner b = data;
//                        b.getContent();
//                        textView.setText(b.getContent().get(0).getAddressLarge());
//                    }
//                });

//***** OR get data with another way :D
        getRetrofitInstance().create(MyApi.class).getBanners().subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseData -> textView.setText(responseData.getContent().get(0).getName()));
    }

}
