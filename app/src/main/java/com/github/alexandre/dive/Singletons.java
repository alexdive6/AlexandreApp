package com.github.alexandre.dive;

import android.content.Context;
import android.content.SharedPreferences;
import com.github.alexandre.dive.data.PokeApi;
import com.github.alexandre.dive.data.PokeDetailApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static PokeApi pokeApiInstance;
    private static PokeDetailApi pokeDetailApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if(gsonInstance == null ){
            gsonInstance = new GsonBuilder().setLenient().create();
        }
        return gsonInstance;
    }

    public static PokeApi getPokeApi(){
        if(pokeApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create(getGson())).build();
            pokeApiInstance = retrofit.create(PokeApi.class);
        }
        return pokeApiInstance;
    }

    public static PokeDetailApi getPokeDetailApi(){
        if(pokeApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create(getGson())).build();
            pokeDetailApiInstance = retrofit.create(PokeDetailApi.class);
        }
        return pokeDetailApiInstance;
    }

    public static SharedPreferences getsharedPreferencesInstance(Context context){
        if(sharedPreferencesInstance == null ){
            sharedPreferencesInstance = context.getSharedPreferences(Constants.PREFERENCES_NAME, Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}