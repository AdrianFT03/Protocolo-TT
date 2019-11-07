package com.example.in_help.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cliente {
    public Cliente() {
    }

    private static final String url = "http://192.168.100.107:3000";

    private  static Retrofit getRetroCliente(){

        Gson gson = new GsonBuilder().create();

        return new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public static APIServer getAPIServer(){
        return getRetroCliente().create(APIServer.class);
    }
}
