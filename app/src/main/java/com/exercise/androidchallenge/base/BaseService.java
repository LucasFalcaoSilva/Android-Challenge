package com.exercise.androidchallenge.base;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseService {

    protected static Object retrofitCreate(Class aClass, String url) {
        okhttp3.OkHttpClient.Builder clientBuilder = new okhttp3.OkHttpClient().newBuilder();
        clientBuilder.readTimeout(30, TimeUnit.SECONDS);
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(clientBuilder.build())
                .baseUrl("http://" + url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(aClass);
    }
}
