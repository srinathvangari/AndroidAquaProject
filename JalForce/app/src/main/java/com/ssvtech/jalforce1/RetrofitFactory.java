package com.ssvtech.jalforce1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

 /*private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-13-233-108-179.ap-south-1.compute.amazonaws.com:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();*/



private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.6:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static Retrofit getRetrofit(){
        return retrofit;

    }


}
