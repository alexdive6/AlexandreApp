package com.github.alexandre.dive.presentation.controller;

import android.content.SharedPreferences;
import com.github.alexandre.dive.Constants;
import com.github.alexandre.dive.Singletons;
import com.github.alexandre.dive.presentation.model.Pokemon;
import com.github.alexandre.dive.presentation.model.PokemonResponse;
import com.github.alexandre.dive.presentation.view.mainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private mainActivity view;

    public MainController( mainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences){
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){
        List<Pokemon> pokemonList = getDataFromCache();
        if(pokemonList != null){
            view.showList(pokemonList);
        }
        else{
            callApi();
        }
    }

    private void callApi() {
        Call<PokemonResponse> call = Singletons.getPokeApi().getPokemonResponse();
        call.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Pokemon> pokemonList = response.body().getResults();
                    saveList(pokemonList);
                    view.showList(pokemonList);
                } else {
                    view.showError();
                }
            }
            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                view.showError();
            }
        });
    }

    private List<Pokemon> getDataFromCache() {
        String jsonPokemon = sharedPreferences.getString(Constants.KEY_POKEMON_LIST, null);
        if (jsonPokemon == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Pokemon>>(){}.getType();
            return gson.fromJson(jsonPokemon, listType);
        }
    }

    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putInt("cle_integer", 3)
                .putString(Constants.KEY_POKEMON_LIST, jsonString)
                .apply();
    }

    public void onItemClick(Pokemon pokemon){
        view.navigateToDetails(pokemon);
    }
}