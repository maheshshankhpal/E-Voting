package com.rigeltech.evoting.rest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by komaltanajimahadik on 15/10/17.
 */

public class ApiClient {
    //public static final String BASE_URL = "http://transport.econext2u.com/api/";
    public static final String BASE_URL = "http://219.90.67.69:5656/N/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
