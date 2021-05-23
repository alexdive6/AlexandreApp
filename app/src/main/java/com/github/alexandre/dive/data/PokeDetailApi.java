package com.github.alexandre.dive.data;

import com.github.alexandre.dive.presentation.model.PokemonDetailResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeDetailApi {
    @GET("/api/v2/pokemon/{pokemon_name}")
    Call<PokemonDetailResponse> getPokemonDetailResponse(
            @Path(value = "pokemon_name", encoded = true) String pokemonName);
}