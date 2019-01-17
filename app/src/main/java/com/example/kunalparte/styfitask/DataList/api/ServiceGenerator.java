package com.example.kunalparte.styfitask.DataList.api;

import com.example.kunalparte.styfitask.Utils.Consts;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit (){
        retrofit = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static <S>S createService(Class<S> sClass){
        return getRetrofit().create(sClass);
    }
}
