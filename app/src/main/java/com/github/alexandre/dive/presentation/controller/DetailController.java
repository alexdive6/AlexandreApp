package com.github.alexandre.dive.presentation.controller;

import android.content.SharedPreferences;
import com.github.alexandre.dive.Singletons;
import com.github.alexandre.dive.presentation.model.Pokemon;
import com.github.alexandre.dive.presentation.model.PokemonDetailResponse;
import com.github.alexandre.dive.presentation.view.DetailActivity;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailController {
    private DetailActivity view;
    private Gson gson;
    private SharedPreferences sharedPreferences;
    private Pokemon pokemon;

    public DetailController(DetailActivity view, Gson gson, SharedPreferences sharedPreferences, Pokemon pokemon) {
        this.view = view;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
        this.pokemon = pokemon;
    }

    public void onStart() {
        callApi();
    }

    private void callApi() {
        Call<PokemonDetailResponse> call = Singletons.getPokeDetailApi().getPokemonDetailResponse(pokemon.getName());
        call.enqueue(new Callback<PokemonDetailResponse>() {

            @Override
            public void onResponse(Call<PokemonDetailResponse> call, Response<PokemonDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                   pokemon.setHeight(response.body().getHeight());
                   pokemon.setWeight(response.body().getWeight());
                   view.showDetail(pokemon);
                }
                else {
                    view.showError();
                }
            }
            @Override
            public void onFailure(Call<PokemonDetailResponse> call, Throwable t) {
                view.showError();
            }
        });
    }
}