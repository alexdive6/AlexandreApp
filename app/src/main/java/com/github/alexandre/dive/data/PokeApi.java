package com.github.alexandre.dive.data;

import com.github.alexandre.dive.presentation.model.PokemonResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {
    @GET("/api/v2/pokemon")
    Call<PokemonResponse> getPokemonResponse();
}