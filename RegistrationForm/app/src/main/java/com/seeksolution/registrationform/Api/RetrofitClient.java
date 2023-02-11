package com.seeksolution.registrationform.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //Singleton class
    //1.private Constructor
    //2.static constant;
    private final static String  BASE_URL="https://json-server.seeksolution.in/";
    private static RetrofitClient instance;
    private Retrofit retrofit;
    //private constructor RetrofitClient;
    private RetrofitClient(){
        Gson gson=new GsonBuilder().setLenient().create();
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
    //synchronise getter to access this instance;
public static  synchronized RetrofitClient getInstance(){
        if (instance==null){
            instance=new RetrofitClient();
        }
        return instance;
}
//interface getter
public Api getApi(){
     return retrofit.create(Api.class);
    }
}
